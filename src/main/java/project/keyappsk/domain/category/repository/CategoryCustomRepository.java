package project.keyappsk.domain.category.repository;

import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.entity.Category;

import java.util.List;

public interface CategoryCustomRepository {
    public List<CategoryStoreDto> findCategoryJoinStoreOnStoreId(Integer storeName);
}
