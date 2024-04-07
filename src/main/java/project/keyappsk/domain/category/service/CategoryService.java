package project.keyappsk.domain.category.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import project.keyappsk.domain.category.dto.CategoryAddFormDto;
import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.entity.Category;
import project.keyappsk.domain.category.repository.CategoryRepository;
import project.keyappsk.domain.store.entity.Store;
import project.keyappsk.domain.store.repository.StoreRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    public void saveCategoryAddFormDto(String storeName , CategoryAddFormDto categoryAddFormDto) {
        Store store = storeRepository.findByName(storeName).get();
        Category category = Category.builder()
                    .name(categoryAddFormDto.getName())
                    .store(store)
                    .build();
        categoryRepository.save(category);
    }
    public List<CategoryStoreDto> findCategoryJoinStoreOnName (String storeName){
        List<CategoryStoreDto> categoryJoinStoreOnId = categoryRepository.findCategoryJoinStoreOnName(storeName);
        return categoryJoinStoreOnId;
    }

}
