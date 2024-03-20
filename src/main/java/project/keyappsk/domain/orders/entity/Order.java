package project.keyappsk.domain.orders.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;
import project.keyappsk.domain.product.entity.Product;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "Member_id", nullable = false)
    Member memberBuyer;

    @ManyToOne
    @JoinColumn(name = "Product_id", nullable = false)
    Product product;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrdersStatus ordersStatus;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

}
