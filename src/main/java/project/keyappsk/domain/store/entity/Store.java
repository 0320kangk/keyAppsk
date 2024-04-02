package project.keyappsk.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "Member_id", nullable = false) //참조 테이블명_필드명(기본키)
    private Member member;

    @Column(nullable = false)
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
    private List<Cart> carts;

}
