package project.keyappsk.domain.orders.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderStoreProductDto {

    @NotNull
    Integer orderId;

    @NotNull
    OrdersStatus ordersStatus;

    @NotBlank
    String storeName;

    @NotBlank
    String jibunAddress;

    @NotBlank
    String roadAddress;

    @NotBlank
    String extraAddress;

    @NotBlank
    String detailAddress;

    @NotNull
    LocalDateTime orderDateTime;


    @NotNull
    LocalDateTime orderUpdateDateTime;

}
