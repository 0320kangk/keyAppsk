package project.keyappsk.domain.category.entity;

import jakarta.persistence.*;
import lombok.*;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.Store;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "Store_id")
    private Store store;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
