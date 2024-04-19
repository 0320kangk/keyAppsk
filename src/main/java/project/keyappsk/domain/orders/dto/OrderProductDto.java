package project.keyappsk.domain.orders.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderProductDto {

    @NotBlank
    String name;

    @NotNull
    Integer count;

    @NotNull
    Integer price;




}
