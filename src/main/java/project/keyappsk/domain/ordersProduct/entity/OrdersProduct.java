package project.keyappsk.domain.ordersProduct.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.product.entity.Product;

@Entity
@Table(name = "ORDERS_PRODUCT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrdersProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "orders_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private int count;

}
