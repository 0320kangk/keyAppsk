package project.keyappsk.domain.orders.repository;

import com.querydsl.core.group.GroupBy;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.orders.dto.OrderProductDto;
import project.keyappsk.domain.orders.dto.OrderStoreDto;
import project.keyappsk.domain.orders.dto.OrderStoreProductDto;
import project.keyappsk.domain.orders.entity.QOrder;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;
import project.keyappsk.domain.ordersProduct.entity.QOrdersProduct;
import project.keyappsk.domain.product.entity.QProduct;
import project.keyappsk.domain.store.dto.StoreSearchDto;
import project.keyappsk.domain.store.entity.QStore;
import static com.querydsl.core.group.GroupBy.list;

import java.util.List;

@RequiredArgsConstructor
public class OrderCustomRepositoryImpl implements OrderCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;


    @Override
    public Page<OrderStoreProductDto> findOrderStoreProductDto(Integer memberId, Pageable pageable) {
        QOrder order = QOrder.order;
        QProduct product = QProduct.product;
        QOrdersProduct ordersProduct = QOrdersProduct.ordersProduct;

        List<OrderStoreProductDto> fetch = jpaQueryFactory
                .selectFrom(order)
                .where(order.memberBuyer.id.eq(memberId))
                .join(order.orderProducts, ordersProduct)
                .join(ordersProduct.product, product)
                .transform(GroupBy.groupBy(order.id).list(
                        Projections.constructor(OrderStoreProductDto.class
                                ,order
                                ,order.store
                                ,list(product)
                                )
                ));

        return new PageImpl<>(fetch, pageable,fetch.size() );
    }
}
