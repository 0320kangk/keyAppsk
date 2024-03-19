package project.keyappsk.domain.product.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
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
    String saveFileName;

}
