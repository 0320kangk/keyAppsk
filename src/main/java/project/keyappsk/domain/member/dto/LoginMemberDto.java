package project.keyappsk.domain.member.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginMemberDto {

    String email;
    String password;
    String name;

}
