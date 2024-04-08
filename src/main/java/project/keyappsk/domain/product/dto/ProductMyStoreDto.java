package project.keyappsk.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductMyStoreDto {

    @NotBlank
    Integer id;
    @NotBlank
    String name;
    @NotBlank
    String categoryName;
    @NotBlank
    Integer price;
    @NotBlank
    Integer count;
    @NotBlank
    ProductStatus status;
    @NotBlank
    String description;
    @NotBlank
    String uploadFileName;
    @NotBlank
    String storeFileName;

}
