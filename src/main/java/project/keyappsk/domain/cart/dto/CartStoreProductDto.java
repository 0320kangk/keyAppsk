package project.keyappsk.domain.cart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartStoreProductDto {

    @NotNull
    Integer productId;
    @NotBlank
    String productName;
    @NotBlank
    Integer storeId;
    @NotNull
    Integer productPrice;
    @NotNull
    Integer productCount;
    @NotNull
    ProductStatus productStatus;
    @NotNull
    Integer cartId;
    @NotNull
    Integer cartCount;
    @NotNull
    String storeFileName;
    @NotNull
    String uploadFileName;

}

