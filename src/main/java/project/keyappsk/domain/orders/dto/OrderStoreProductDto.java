package project.keyappsk.domain.orders.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    Integer storeId;

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
    List<OrderProductDto> orderProductDtos = new ArrayList<>();

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    String orderCreateDateTime;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    String orderUpdateDateTime;

    @QueryProjection
    public OrderStoreProductDto(Order order,
                                Store store,
                                List<Product> products // OrderProductDto 대신 Product으로 수정
                              ) {
        this.orderId = order.getId();
        this.storeId = store.getId();
        this.ordersStatus = order.getOrdersStatus();
        this.storeName = store.getName();
        this.jibunAddress = store .getJibunAddress();
        this.roadAddress = store.getRoadAddress();
        this.extraAddress = store.getExtraAddress();
        this.detailAddress = store.getDetailAddress();
        products.forEach( (product -> {
            orderProductDtos.add( OrderProductDto.builder()
                    .name(product.getName())
                    .count(product.getCount())
                    .price(product.getPrice())
                    .build());
        }) );
        this.orderCreateDateTime = order.getCreatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        this.orderUpdateDateTime = order.getUpdatedDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
