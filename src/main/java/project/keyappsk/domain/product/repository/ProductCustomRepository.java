package project.keyappsk.domain.product.repository;

import project.keyappsk.domain.product.dto.ProductDetailFormDto;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductCustomRepository {
    List<ProductMyStoreDto> getProductMyStoreDto(Integer storeId);
    Optional<ProductDetailFormDto> findByIdProductDetailFormDto(Integer productId);

    List<Product> findByStoreId(Integer storeId);
}
