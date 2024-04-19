package project.keyappsk.domain.orders.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.orders.dto.OrderStoreProductDto;

public interface OrderCustomRepository {
    Page<OrderStoreProductDto> findMyOrderStoreProductDto(Integer memberId, Pageable pageable);
    Page<OrderStoreProductDto> findMyOrderedStoreProductDto(Integer memberId, Pageable pageable);
}
