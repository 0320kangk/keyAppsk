package project.keyappsk.domain.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.product.entity.ProductImage;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {

}
