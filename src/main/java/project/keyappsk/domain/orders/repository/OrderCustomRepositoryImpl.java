package project.keyappsk.domain.orders.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.orders.dto.OrderProductDto;
import project.keyappsk.domain.orders.dto.OrderStoreProductDto;
import project.keyappsk.domain.orders.entity.QOrder;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;
import project.keyappsk.domain.ordersProduct.entity.QOrdersProduct;
import project.keyappsk.domain.store.dto.StoreSearchDto;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public Page<OrderStoreProductDto> findOrderStoreProductDto(Integer memberId, Pageable pageable) {
        QOrder order = QOrder.order;
        QOrdersProduct ordersProduct = QOrdersProduct.ordersProduct;

        List<OrderStoreProductDto> fetch = jpaQueryFactory
                .select(Projections.constructor(OrderStoreProductDto.class
                        , order.id, order.ordersStatus, order.store.name,
                        order.store.jibunAddress, order.store.roadAddress, order.store.extraAddress
                        , order.store.detailAddress,
                        order.createdDate,
                        order.updatedDate
                ))
                .from(order)
                .where(order.memberBuyer.id.eq(memberId))
                .fetch();

        return new PageImpl<>(fetch, pageable,fetch.size() );
    }
}
