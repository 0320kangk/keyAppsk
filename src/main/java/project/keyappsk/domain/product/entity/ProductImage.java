package project.keyappsk.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    @ToString.Exclude
    Product product;

    @Column(nullable = false)
    String uploadFileName;

    @Column(nullable = false)
    String storeFileName;

}
