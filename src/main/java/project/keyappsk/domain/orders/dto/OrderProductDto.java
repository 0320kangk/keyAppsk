package project.keyappsk.domain.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderProductDto {

    @NotBlank
    String name;

    @NotNull
    Integer count;

    @NotNull
    Integer price;


}
