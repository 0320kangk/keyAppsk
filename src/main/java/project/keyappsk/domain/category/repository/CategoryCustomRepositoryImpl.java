package project.keyappsk.domain.category.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.entity.QCategory;
import project.keyappsk.domain.store.entity.QStore;
import project.keyappsk.domain.store.entity.Store;

import java.util.List;

@RequiredArgsConstructor
public class CategoryCustomRepositoryImpl implements CategoryCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<CategoryStoreDto> findCategoryStoreWhereStoreId(Integer storeId) {
        QStore store = QStore.store;
        QCategory category = QCategory.category;
        List<CategoryStoreDto> fetch = jpaQueryFactory.select(Projections.constructor(CategoryStoreDto.class, category.id,category.name))
                .from(category)
                .where(category.store.id.eq(storeId))
                .fetch();
        return fetch;
    }
    @Override
    public boolean checkStoreCategoryDuplicates(Integer storeId, String categoryName){
        QStore store = QStore.store;
        QCategory category = QCategory.category;
        BooleanExpression storeExists = store.id.eq(storeId);
        BooleanExpression categoryExists = store.categories.any().name.eq(categoryName);
        BooleanExpression condition = storeExists.and(categoryExists);
        List<Store> stores = jpaQueryFactory.selectFrom(store).where(condition).fetch();
        return stores.isEmpty();
    }
}
