package project.keyappsk.domain.category.repository;

import project.keyappsk.domain.category.dto.CategoryStoreDto;

import java.util.List;

public interface CategoryCustomRepository {
    public List<CategoryStoreDto> findCategoryStoreWhereStoreId(Integer storeName);
    public boolean checkStoreCategoryDuplicates (Integer storeId, String categoryName);
}
