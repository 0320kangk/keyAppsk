package project.keyappsk.domain.alarm.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import project.keyappsk.domain.alarm.dto.AlarmMessageResponseDto;
import project.keyappsk.domain.alarm.entity.AlarmMessage;
import project.keyappsk.domain.alarm.repository.alarmRepository.AlarmMessageRepository;
import project.keyappsk.domain.alarm.repository.emitterRepository.EmitterRepositoryImpl;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class AlarmService {
    private final Long DEFAULT_TIMEOUT = 60L * 1000L * 60L;
    private final EmitterRepositoryImpl emitterRepository;
    private final AlarmMessageRepository alarmMessageRepository;


    @Transactional
    public void readCheckAll(String memberId) {
        Map<String, Object> allEventCacheStartWithByMemberId = emitterRepository.findAllEventCacheStartWithByMemberId(memberId);
        allEventCacheStartWithByMemberId.forEach( ( id,  value ) -> {
                    AlarmMessage notifications = (AlarmMessage) value;
                    notifications.setIsRead(true);
                    allEventCacheStartWithByMemberId.put(id, notifications);
                }
        );
        List<AlarmMessage> byReceiver_id = alarmMessageRepository.findByReceiver_Id(Integer.valueOf(memberId));
        byReceiver_id.forEach( (notifications) -> {
            notifications.setIsRead(true);
        } );
        alarmMessageRepository.saveAll(byReceiver_id);
    }
    @Transactional
    public void readCheck(String memberId, Integer alarmId) {
        Map<String, Object> allEventCacheStartWithByMemberId = emitterRepository.findAllEventCacheStartWithByMemberId(memberId);
        allEventCacheStartWithByMemberId.forEach( ( id,  value ) -> {
                    AlarmMessage alarmMessage = (AlarmMessage) value;
                    if( alarmMessage.getId() == alarmId) alarmMessage.setIsRead(true);
                    allEventCacheStartWithByMemberId.put(id, alarmMessage);
                }
        );
        AlarmMessage alarmMessage = alarmMessageRepository.findById(alarmId).orElseThrow(() -> new IllegalArgumentException());
        alarmMessage.setIsRead(true);
        alarmMessageRepository.save(alarmMessage);
    }



    public SseEmitter subscribe(Integer memberId, String lastEventId) {
        String emitterId = makeTimeIncludeId(memberId);
        SseEmitter emitter = emitterRepository.save(emitterId, new SseEmitter(DEFAULT_TIMEOUT));

        emitter.onCompletion( () -> emitterRepository.deleteById(emitterId) );
        emitter.onTimeout(() -> emitterRepository.deleteById(emitterId));

        //505 에러를 방지하기 위한 더미 이벤트 전송
        //등록 후 SseEmitter 유효시간동안 어느 데이터도 전송되지 않는 다면 503 에러를 발생시키므로 이것에 대한 방지로 더이 이벤트 발생
        String eventId = makeTimeIncludeId(memberId);
        sendAlarmMessage(emitter, eventId, emitterId, "EventStream Created. [userId=" + memberId + "]");

        if (hasLostData(lastEventId)) {
            sendLostData(lastEventId, memberId, emitterId, emitter);
        }else {
            PageRequest pageable = PageRequest.of(0, 15, Sort.by("createdDate").descending());
            List<AlarmMessage> content = alarmMessageRepository.findByReceiver_Id(memberId, pageable).getContent();
            content.forEach( (alarmMessage -> {
                if(!alarmMessage.getIsRead()) sendAlarmMessage(emitter, eventId, emitterId, AlarmMessageResponseDto.create(alarmMessage));
            }) );
        }
        return emitter;
    }
    private String makeTimeIncludeId(Integer memberId) {

        return memberId + "_" + System.currentTimeMillis();
    }

    private void sendAlarmMessage(SseEmitter emitter, String eventId, String emitterId, Object data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(eventId)
                    .data(data, MediaType.APPLICATION_JSON));

        } catch (IOException exception) {
            emitterRepository.deleteById(emitterId);
        }
    }


    private boolean hasLostData(String lastEventId) {

        return !lastEventId.isEmpty();
    }
    private void sendLostData(String lastEventId,Integer memberId, String emitterId, SseEmitter emitter) {

        Map<String, Object> eventCaches = emitterRepository.findAllEventCacheStartWithByMemberId(String.valueOf(memberId));
        eventCaches.entrySet().stream()
                .filter(entry -> lastEventId.compareTo(entry.getKey()) < 0 )
                .forEach(entry -> sendAlarmMessage(emitter, entry.getKey(), emitterId, entry.getValue()));
    }

    @Transactional
    public void send(Member receiver,
                     String sender,
                     String storeName,
                     OrdersStatus ordersStatus
    ) {
        String message = createMessage(sender ,storeName, ordersStatus);
        String url = createUrl(ordersStatus);
        log.info("message {}", message);
        log.info("url {}", url);
        AlarmMessage alarmMessage = createAlarmMessage(receiver, message, url);
        AlarmMessage sanedAlarmMessage = alarmMessageRepository.save(alarmMessage);
        log.info("message: {}", sanedAlarmMessage.toString());
        String receiverId = String.valueOf(receiver.getId());
        String eventId = receiverId + "_" + System.currentTimeMillis();
        Map<String, SseEmitter> emitters = emitterRepository.findAllEmitterStartWithByMemberId(receiverId);
        emitters.forEach(
                (key, emitter) -> {
                    emitterRepository.saveEventCache(key, sanedAlarmMessage);
                    sendAlarmMessage(emitter, eventId, key, AlarmMessageResponseDto.create(sanedAlarmMessage)  );
                }
        );
    }
    public String createMessage(String sender, String storeName, OrdersStatus orderStatus) {
        //memberId = 구매자
        String message = "";
        if (orderStatus == OrdersStatus.WAITING) {
            message = sender + "님이 " + storeName +"에 주문합니다.";
        } else if (orderStatus == OrdersStatus.PREPARATION ) {
            message = sender + "님의 " + storeName + "에 " +  orderStatus.getTitle() + " 되었습니다." ;
        } else if (orderStatus == OrdersStatus.COMPLETE) {
            message = sender + "님의 " + storeName + "에 " + orderStatus.getTitle()+ " 되었습니다.";
        } else if (orderStatus == OrdersStatus.CANCEL) {
            message = sender + "님의 " + storeName + "에 " + orderStatus.getTitle() + " 되었습니다.";
        }
        return message;
    }

    public String createUrl(OrdersStatus ordersStatus) {

        String url = "";

        if (ordersStatus == OrdersStatus.WAITING) {
            url = "/order";
        } else if (ordersStatus == OrdersStatus.COMPLETE ||
                ordersStatus == OrdersStatus.PREPARATION ||
                ordersStatus == OrdersStatus.CANCEL) {
            url = "/order/store";
        }
        return url;
    }


    private AlarmMessage createAlarmMessage(Member receiver, String message, String url) {
        return AlarmMessage.builder()
                .receiver(receiver)
                .message(message)
                .url(url)
                .isRead(false)
                .createdDate(LocalDateTime.now())
                .build();
    }



}
