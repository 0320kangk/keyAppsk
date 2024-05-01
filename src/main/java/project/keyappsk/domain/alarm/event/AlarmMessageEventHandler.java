package project.keyappsk.domain.alarm.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import project.keyappsk.domain.alarm.dto.AlarmMessageRequestDto;
import project.keyappsk.domain.alarm.service.AlarmService;

@Component
@RequiredArgsConstructor
@Slf4j
public class AlarmMessageEventHandler {
    private final AlarmService alarmService;
    @EventListener
    @Async
    public void handleAlarmMessageSend(AlarmMessageRequestDto alarmMessageRequestDto) {
        log.info("event listener");
        log.info("alarmMessageRequestDto {}",  alarmMessageRequestDto);
        alarmService.send( alarmMessageRequestDto.getReceiver(),
                alarmMessageRequestDto.getSender(),
                alarmMessageRequestDto.getStoreName(),
                alarmMessageRequestDto.getOrdersStatus());
    }
}
