package project.keyappsk.queryDsl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import project.keyappsk.domain.alarm.entity.AlarmMessage;
import project.keyappsk.domain.alarm.repository.alarmRepository.AlarmMessageRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.repository.MemberRepository;

import java.time.LocalDateTime;

@SpringBootTest
@RequiredArgsConstructor
@Slf4j
public class alarmTest {

    @Autowired
    AlarmMessageRepository alarmMessageRepository;

    @Autowired
    MemberRepository memberRepository;

    @Test
    @DisplayName("알람 메시지 SAVE TEST")
    public void alarmMessageSaveTest(){
        Member member = memberRepository.findById(2).orElseThrow(() -> new IllegalArgumentException());
        AlarmMessage alarmMessage = AlarmMessage.builder()
                .receiver(member)
                .message("test")
                .url("/asad")
                .isRead(false)
                .createdDate(LocalDateTime.now())
                .build();
        AlarmMessage save = alarmMessageRepository.save(alarmMessage);
        log.info("save {}", save);
    }



}
