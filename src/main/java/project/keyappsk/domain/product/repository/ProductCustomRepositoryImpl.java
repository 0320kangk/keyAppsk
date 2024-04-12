package project.keyappsk.domain.product.repository;

import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.keyappsk.domain.product.dto.ProductDetailFormDto;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.entity.QProduct;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ProductMyStoreDto> findByStoreId(Integer storeId) {
        QProduct product = QProduct.product;
        List<ProductMyStoreDto> productMyStoreDtos = jpaQueryFactory.select(Projections.constructor(ProductMyStoreDto.class,
                        product.id, product.name,product.category.name, product.price, product.count, product.productStatus, product.description,
                        product.productImage.uploadFileName, product.productImage.storeFileName))
                .from(product)
                .where(product.store.id.eq(storeId))
                .fetch();
        return productMyStoreDtos;
    }

    @Override
    public Optional<ProductDetailFormDto> findByIdProductDetailFormDto(Integer productId) {
        QProduct product = QProduct.product;
        List<ProductDetailFormDto> fetch = jpaQueryFactory.select(Projections.constructor(ProductDetailFormDto.class,
                        product.id, product.name,product.category.name, product.price, product.count,
                        product.productStatus, product.description, product.productImage.storeFileName))
                .from(product)
                .where(product.id.eq(productId))
                .fetch();

        return Optional.of(fetch.get(0));

    }
}

