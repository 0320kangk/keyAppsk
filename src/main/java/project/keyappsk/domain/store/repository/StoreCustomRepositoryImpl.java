package project.keyappsk.domain.store.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.store.dto.StoreSearchDto;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

@RequiredArgsConstructor
public class StoreCustomRepositoryImpl implements  StoreCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<StoreSearchDto> findByRoadAddressContainingOrJibunAddressContaining(String query, Pageable pageable) {
        QStore store = QStore.store;
        List<StoreSearchDto> fetch = jpaQueryFactory.select(Projections.constructor(
                        StoreSearchDto.class, store.id, store.name,store.storeStatus,
                        store.roadAddress, store.jibunAddress, store.storeImage.storeFileName))
                .from(store)
                .where(store.jibunAddress.like("%" + query + "%")
                        .or(store.roadAddress.like("%" + query + "%")))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(fetch, pageable, fetch.size());
    }

}
