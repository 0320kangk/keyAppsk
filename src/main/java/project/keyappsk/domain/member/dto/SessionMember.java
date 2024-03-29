package project.keyappsk.domain.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import project.keyappsk.domain.member.entity.Member;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class SessionMember implements Serializable {
    private String name;
    private String email;

    public SessionMember(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
    }
}