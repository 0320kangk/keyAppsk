package project.keyappsk.domain.product.repository;

import project.keyappsk.domain.product.dto.ProductMyStoreDto;

import java.util.List;

public interface ProductCustomRepository {
    List<ProductMyStoreDto> findByStoreId(Integer storeId);
}
