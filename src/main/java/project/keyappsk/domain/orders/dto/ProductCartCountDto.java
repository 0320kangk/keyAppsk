package project.keyappsk.domain.orders.dto;

import lombok.*;
import project.keyappsk.domain.product.entity.Product;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductCartCountDto {
    Product product;
    Integer count;
}
