package project.keyappsk.domain.category.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.Store;

import java.util.List;

@Entity
@Getter
@Setter
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "Store_id")
    private Store store;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

}
