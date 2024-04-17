package project.keyappsk.domain.ordersProduct.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;

@Repository
public interface OrdersProductRepository extends JpaRepository<OrdersProduct, Integer> {

}
