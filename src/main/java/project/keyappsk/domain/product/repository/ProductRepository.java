package project.keyappsk.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, ProductCustomRepository {
}
