package project.keyappsk.domain.product.dto;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.ConstructorExpression;
import javax.annotation.processing.Generated;

/**
 * project.keyappsk.domain.product.dto.QProductDetailsStoreDto is a Querydsl Projection type for ProductDetailsStoreDto
 */
@Generated("com.querydsl.codegen.DefaultProjectionSerializer")
public class QProductDetailsStoreDto extends ConstructorExpression<ProductDetailsStoreDto> {

    private static final long serialVersionUID = 1614214346L;

    public QProductDetailsStoreDto(com.querydsl.core.types.Expression<? extends project.keyappsk.domain.store.entity.Store> store) {
        super(ProductDetailsStoreDto.class, new Class<?>[]{project.keyappsk.domain.store.entity.Store.class}, store);
    }

}

