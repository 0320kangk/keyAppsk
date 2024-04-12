package project.keyappsk.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailFormDto {
    @NotNull
    Integer id;
    @NotBlank
    String name;
    @NotBlank
    String categoryName;
    @NotNull
    Integer price;
    @NotNull
    Integer count;
    @NotBlank
    ProductStatus status;
    @NotBlank
    String description;
    @NotBlank
    String storeFileName;
}
