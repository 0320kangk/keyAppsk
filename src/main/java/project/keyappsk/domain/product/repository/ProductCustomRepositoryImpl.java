package project.keyappsk.domain.product.repository;

import com.querydsl.core.types.Operation;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.product.dto.ProductDetailFormDto;
import project.keyappsk.domain.product.dto.ProductDetailsStoreDto;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.entity.QProduct;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;
import project.keyappsk.domain.store.dto.StoreProductDto;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class ProductCustomRepositoryImpl implements ProductCustomRepository {

    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ProductMyStoreDto> getProductMyStoreDto(Integer storeId) {
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
    @Override
    public Page<StoreProductDto> getStoreProductDto(Integer storeId, Pageable page){
        QProduct product = QProduct.product;
        List<StoreProductDto> storeProductDtos = jpaQueryFactory.select(Projections.constructor(StoreProductDto.class,
                        product.id, product.name,product.category.name, product.price, product.count, product.productStatus, product.description,
                        product.productImage.uploadFileName, product.productImage.storeFileName))
                .from(product)
                .where(product.store.id.eq(storeId).and(product.productStatus.eq(ProductStatus.SALE)))
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetch();
        Long totalCount = jpaQueryFactory.select(product.count())
                .from(product)
                .where(product.store.id.eq(storeId).and(product.productStatus.eq(ProductStatus.SALE)))
                .offset(page.getOffset())
                .limit(page.getPageSize())
                .fetchOne();
        return new PageImpl<>(storeProductDtos, page, totalCount);

    }
    @Override
    public List<Product> findByStoreId(Integer storeId){
        QProduct product = QProduct.product;
        return jpaQueryFactory.select(product)
                .from(product)
                .where(product.id.eq(storeId))
                .fetch();
    }

    @Override
    public Optional<ProductDetailsStoreDto> getProductDetailsStoreDto(Integer storeId){
        QProduct product = QProduct.product;
        return Optional.ofNullable(jpaQueryFactory.select(Projections.constructor(ProductDetailsStoreDto.class,
                        product.store))
                .from(product)
                .where(product.id.eq(storeId))
                .fetchOne());
    }



}

