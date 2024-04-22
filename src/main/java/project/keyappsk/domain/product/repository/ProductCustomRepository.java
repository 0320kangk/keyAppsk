package project.keyappsk.domain.product.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.product.dto.ProductDetailFormDto;
import project.keyappsk.domain.product.dto.ProductDetailsStoreDto;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.dto.StoreProductDto;

import java.util.List;
import java.util.Optional;

public interface ProductCustomRepository {
    List<ProductMyStoreDto> getProductMyStoreDto(Integer storeId);

    Page<StoreProductDto> getStoreProductDto(Integer storeId, Pageable page);

    Optional<ProductDetailFormDto> findByIdProductDetailFormDto(Integer productId);

    List<Product> findByStoreId(Integer storeId);

    Optional<ProductDetailsStoreDto> getProductDetailsStoreDto(Integer storeId);

}