package project.keyappsk.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.keyappsk.domain.category.dto.CategoryAddFormDto;
import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.entity.Category;
import project.keyappsk.domain.category.repository.CategoryRepository;
import project.keyappsk.domain.store.entity.Store;
import project.keyappsk.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    public void saveCategoryAddFormDto(Integer storeId , CategoryAddFormDto categoryAddFormDto) {
        Store store = storeRepository.findById(storeId).get();
        Category category = Category.builder()
                    .name(categoryAddFormDto.getName())
                    .store(store)
                    .build();

        if(categoryRepository.checkStoreCategoryDuplicates(storeId, category.getName())){
            categoryRepository.save(category);
        }
    }
    public List<CategoryStoreDto> getCategoryStoreDto (Integer storeId){
        return categoryRepository.findCategoryStoreWhereStoreId(storeId);
    }

    public Integer findStoreById(Integer categoryId){
        return categoryRepository.findById(categoryId).get().getStore().getId();
    }

}
