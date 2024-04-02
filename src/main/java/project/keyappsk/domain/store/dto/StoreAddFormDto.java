package project.keyappsk.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreAddFormDto {

    @NotBlank
    private String name;

    @NotBlank
    private String roadAddress;

    @NotBlank
    private String jibunAddress;

    @NotBlank
    private String detailAddress;

    @NotBlank
    private String extraAddress;

    private MultipartFile multipartFile; //이미지

}
