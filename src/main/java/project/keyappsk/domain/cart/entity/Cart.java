package project.keyappsk.domain.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.Store;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    Integer productCount;

    @ManyToOne
    @JoinColumn(name = "Member_id")
    Member member;

    @ManyToOne
    @JoinColumn(name = "Product_id")
    Product product;

    @ManyToOne
    @JoinColumn(name = "Store_id")
    Store store;

}
