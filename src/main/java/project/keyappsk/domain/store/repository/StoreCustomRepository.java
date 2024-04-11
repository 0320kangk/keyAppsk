package project.keyappsk.domain.store.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.store.dto.StoreSearchDto;

import java.util.List;

public interface StoreCustomRepository {

    Page<StoreSearchDto> findByRoadAddressContainingOrJibunAddressContaining(String query, Pageable pageable);


}
