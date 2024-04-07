package project.keyappsk.domain.category.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.entity.QCategory;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<CategoryStoreDto> findCategoryJoinStoreOnName(String storeName) {
        QStore store = QStore.store;
        QCategory category = QCategory.category;
        List<CategoryStoreDto> fetch = jpaQueryFactory.select(Projections.constructor(CategoryStoreDto.class, category.name))
                .from(category)
                .join(store)
                .on(store.name.eq(storeName))
                .fetch();
        return fetch;
    }
}
