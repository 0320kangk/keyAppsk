package project.keyappsk.domain.alarm.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.member.entity.Member;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class AlarmMessage {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Member_id" ,nullable = false)
    @ToString.Exclude
    private Member receiver;

    @Column(nullable = false)
    private String message;

    @Column(nullable = false)
    private String url;


    @Column(nullable = false)
    private Boolean isRead;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    public void changeMembers(Member member){
        this.receiver = member;
        member.getAlarmMessages().add(this);
    }

}
