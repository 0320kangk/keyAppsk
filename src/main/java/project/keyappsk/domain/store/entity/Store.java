package project.keyappsk.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.category.entity.Category;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "Member_id", nullable = false) //참조 테이블명_필드명(기본키)
    @ToString.Exclude
    private Member member;

    @Column(unique = true ,nullable = false)
    private String name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;

    @Column(nullable = false)
    String roadAddress;

    @Column(nullable = false)
    String jibunAddress;

    @Column(nullable = false)
    String detailAddress;

    @Column(nullable = false)
    String extraAddress;

    @OneToOne(mappedBy = "store")
    StoreImage storeImage;

    @Column(nullable = false)
    private LocalDateTime createdDate;

    @Column(nullable = false)
    private LocalDateTime updatedDate;

    @OneToMany(mappedBy = "store")
    @ToString.Exclude
    private List<Cart> carts;

    @OneToMany(mappedBy = "store")
    private List<Product> products;

    @OneToMany(mappedBy = "store")
    private List<Order> orders;

    @OneToMany(mappedBy = "store")
    private List<Category> categories;

}
