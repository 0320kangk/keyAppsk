package project.keyappsk.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.keyappsk.domain.alarm.entity.enumerate.ReadStatus;
import project.keyappsk.domain.members.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class AlarmMessage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Member_id" ,nullable = false)
    private Member member;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ReadStatus readStatus;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    public void changeMembers(Member member){
        this.member = member;
        member.getAlarmMessageList().add(this);
    }

}
