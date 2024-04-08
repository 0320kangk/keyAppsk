package project.keyappsk.domain.product.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateFormDto {

    @NotBlank
    String name;
    @NotBlank
    Integer price;
    @NotBlank
    Integer count;
    @NotBlank
    ProductStatus status;
    @NotBlank
    String description;

}
