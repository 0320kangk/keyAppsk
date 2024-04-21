package project.keyappsk.domain.category.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.category.entity.Category;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>, CategoryCustomRepository{
    Optional<Category> findByName(String name);
}
