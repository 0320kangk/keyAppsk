package project.keyappsk.domain.store.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.store.dto.StoreSearchDto;
import project.keyappsk.domain.store.entity.QStore;
import project.keyappsk.domain.store.entity.Store;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class StoreCustomRepositoryImpl implements  StoreCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<StoreSearchDto> findByRoadAddressContainingOrJibunAddressContaining(String query, Pageable pageable) {
        QStore store = QStore.store;


        BooleanExpression whereClause = store.jibunAddress.like("%" + query + "%")
                .or(store.roadAddress.like("%" + query + "%"));

        List<StoreSearchDto> fetch = jpaQueryFactory.select(Projections.constructor(
                        StoreSearchDto.class, store.id, store.name,store.storeStatus,
                        store.roadAddress, store.jibunAddress, store.storeImage.storeFileName))
                .from(store)
                .where(whereClause)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        Long totalCount = jpaQueryFactory.select(store.count())
                .from(store)
                .where(whereClause)
                .fetchOne();

        log.info("fetch size{}", fetch.size());
        log.info("pageable offset", pageable.getOffset());

        return new PageImpl<>(fetch, pageable, totalCount);
    }
    @Override
    public List<Store> findStoreByMemberId(Integer memberId){
        QStore store = QStore.store;

        return jpaQueryFactory
                .selectFrom(store)
                .join(store.member).fetchJoin()
                .where(store.member.id.eq(memberId))
                .fetch();
    }

}
