package project.keyappsk.domain.store.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import project.keyappsk.domain.store.entity.Store;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store,  Integer> {

    Optional<Store> findByName(String name);
}
