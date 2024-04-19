package project.keyappsk.domain.orders.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.keyappsk.domain.orders.dto.QOrderStoreProductDto is a Querydsl Projection type for OrderStoreProductDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QOrderStoreProductDto extends ConstructorExpression<OrderStoreProductDto> {

    private static final long serialVersionUID = -1666626064L;

    public QOrderStoreProductDto(com.querydsl.core.types.Expression<? extends project.keyappsk.domain.orders.entity.Order> order, com.querydsl.core.types.Expression<? extends project.keyappsk.domain.store.entity.Store> store, com.querydsl.core.types.Expression<? extends java.util.List<project.keyappsk.domain.product.entity.Product>> products) {
        super(OrderStoreProductDto.class, new Class<?>[]{project.keyappsk.domain.orders.entity.Order.class, project.keyappsk.domain.store.entity.Store.class, java.util.List.class}, order, store, products);
    }

}

