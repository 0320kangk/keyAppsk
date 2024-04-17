package project.keyappsk.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.category.entity.Category;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;
import project.keyappsk.domain.product.entity.enumerate.ProductStatus;
import project.keyappsk.domain.store.entity.Store;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @ManyToOne
    @JoinColumn(name = "Store_id", nullable = false)
    @ToString.Exclude
    Store store;

    @ManyToOne
    @JoinColumn(name ="Category_id", nullable = false)
    @ToString.Exclude
    Category category;

    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    ProductImage productImage;

    @Column(nullable = false)
    String name;

    @Column(nullable = false)
    int price;

    @Column(nullable = false)
    int count;

    @Column(nullable = false)
    String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @Column(nullable = false)
    LocalDateTime createdDate;

    @Column(nullable = false)
    LocalDateTime updatedDate;

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<OrdersProduct> orderProducts = new ArrayList<>();

    @OneToMany(mappedBy = "product")
    @ToString.Exclude
    private List<Cart> carts;

}
