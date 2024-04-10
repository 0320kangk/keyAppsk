package project.keyappsk.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductUpdateFormDto {

    @NotBlank
    String name;
    @NotNull
    Integer price;
    @NotNull
    Integer count;
    @NotNull
    Boolean status;
    @NotBlank
    String description;

    MultipartFile image;

}
