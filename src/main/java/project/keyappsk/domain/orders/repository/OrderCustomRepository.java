package project.keyappsk.domain.orders.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.orders.dto.OrderStoreProductDto;
import project.keyappsk.domain.store.dto.StoreSearchDto;

public interface OrderCustomRepository {
    Page<OrderStoreProductDto> findOrderStoreProductDto(Integer memberId, Pageable pageable);
}
