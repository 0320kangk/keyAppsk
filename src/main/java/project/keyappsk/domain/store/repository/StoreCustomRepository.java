package project.keyappsk.domain.store.repository;

import jakarta.persistence.criteria.CriteriaBuilder;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;

import java.util.List;

public interface StoreCustomRepository {
    List<ProductMyStoreDto> findByIdFetch(Integer storeId);

}
