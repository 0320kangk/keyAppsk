package project.keyappsk.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductAddFormDto {

    @NotBlank
    String name;

    @NotBlank
    Integer price;

    @NotBlank
    Integer count;

    @NotBlank
    String description;

    @NotBlank
    private MultipartFile image; //이미지


}
