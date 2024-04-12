package project.keyappsk.domain.product.repository;

import project.keyappsk.domain.product.dto.ProductDetailFormDto;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;

import java.util.List;
import java.util.Optional;

public interface ProductCustomRepository {
    List<ProductMyStoreDto> findByStoreId(Integer storeId);
    Optional<ProductDetailFormDto> findByIdProductDetailFormDto(Integer productId);
}
