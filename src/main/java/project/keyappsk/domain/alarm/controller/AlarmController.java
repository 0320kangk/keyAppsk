package project.keyappsk.domain.alarm.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import project.keyappsk.domain.alarm.service.AlarmService;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.member.entity.Member;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/alarm")
public class AlarmController {

    private final AlarmService alarmService;

    @GetMapping(value = "/subscribe", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter subscribe(@RequestHeader(value = "Last-Event-ID", required = false, defaultValue = "") String lastEventId,
                                @AuthenticationPrincipal CustomUserDetails customUserDetails){
        Member member = customUserDetails.getMember();
        log.info("lastEventId {}", lastEventId);
        SseEmitter sseEmitter = alarmService.subscribe(member.getId(), lastEventId);
        return sseEmitter;
    }
    @GetMapping(value = "/read")
    public void readAll(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        alarmService.readCheckAll( String.valueOf(member.getId()));
    }
    @GetMapping(value = "/read/{alarmId}")
    public void read(@PathVariable("alarmId") Integer alarmId,
            @AuthenticationPrincipal CustomUserDetails customUserDetails) {
        Member member = customUserDetails.getMember();
        alarmService.readCheck( String.valueOf(member.getId()), alarmId);
    }


}
