package project.keyappsk.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreProductDto {

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
    String uploadFileName;
    @NotBlank
    String storeFileName;

}
