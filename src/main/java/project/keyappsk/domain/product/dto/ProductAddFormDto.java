package project.keyappsk.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    Integer price;

    @NotNull
    Integer count;

    @NotBlank
    String description;

    @NotNull
    private MultipartFile image; //이미지


}
