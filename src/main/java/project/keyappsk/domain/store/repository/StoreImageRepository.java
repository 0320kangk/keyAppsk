package project.keyappsk.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.store.entity.StoreImage;

@Repository
public interface StoreImageRepository extends JpaRepository<StoreImage, Integer> {


}
