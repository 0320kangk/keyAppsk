package project.keyappsk.domain.product.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @OneToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Column(nullable = false)
    String uploadFileName;

    @Column(nullable = false)
    String storeFileName;

}
