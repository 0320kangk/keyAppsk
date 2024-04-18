package project.keyappsk.domain.orders.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "Orders")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name="Store_id", nullable = false)
    Store store;

    @ManyToOne
    @JoinColumn(name = "Member_id", nullable = false)
    Member memberBuyer;

    @OneToMany(mappedBy = "order")
    private List<OrdersProduct> orderProducts = new ArrayList<>();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    OrdersStatus ordersStatus;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

}
