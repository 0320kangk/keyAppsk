package project.keyappsk.domain.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.orders.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>, OrderCustomRepository {

}
