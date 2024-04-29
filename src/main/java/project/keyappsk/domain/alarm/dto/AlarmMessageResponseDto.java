package project.keyappsk.domain.alarm.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.keyappsk.domain.alarm.entity.AlarmMessage;

import java.time.LocalDateTime;
@Getter
@Setter
@Builder
public class AlarmMessageResponseDto {

    Integer id;
    String message;
    String url;
    Boolean isRead;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    LocalDateTime createdDateTime;

    public static AlarmMessageResponseDto create(AlarmMessage alarmMessage) {
        return AlarmMessageResponseDto.builder()
                .id(alarmMessage.getId())
                .message(alarmMessage.getMessage())
                .url(alarmMessage.getUrl())
                .isRead(alarmMessage.getIsRead())
                .createdDateTime(alarmMessage.getCreatedDate())
                .build();
    }
}
