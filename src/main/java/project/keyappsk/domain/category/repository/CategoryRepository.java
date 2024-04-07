package project.keyappsk.domain.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.category.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryCustomRepository{

}
