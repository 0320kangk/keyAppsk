package project.keyappsk.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddMemberFormDto {

    @NotBlank
    String email;
    @NotBlank
    String password;
    @NotBlank
    String name;

}
