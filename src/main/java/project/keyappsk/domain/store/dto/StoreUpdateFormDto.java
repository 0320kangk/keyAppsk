package project.keyappsk.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreUpdateFormDto {

    @NotBlank
    private String name;

    @NotBlank
    private String roadAddress;

    @NotBlank
    private String jibunAddress;

    @NotBlank
    private String detailAddress;

    @NotNull
    Boolean status;

    @NotBlank
    private String extraAddress;

    private MultipartFile image; //이미지

}
