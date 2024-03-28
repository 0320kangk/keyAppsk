package project.keyappsk.domain.member.dto;


import lombok.Getter;
import project.keyappsk.domain.member.entity.Member;

import java.io.Serializable;

@Getter
public class SessionMember implements Serializable {
    private String name;
    private String email;

    public SessionMember(Member user) {
        this.name = user.getName();
        this.email = user.getEmail();
    }
}