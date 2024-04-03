package project.keyappsk.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class StoreImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Store_id", nullable = false)
    @ToString.Exclude
    private Store store;

    @Column(nullable = false)
    private String uploadFileName;  //회원이 올린 파일 이름

    @Column(nullable = false)
    private String storeFileName;   //실제 저장 될 파일 이름
    @Builder
    public StoreImage(String uploadFileName, String storeFileName) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
    }

    public void storeChange(Store store){
        this.store = store;
        store.setStoreImage(this);
    }
}
