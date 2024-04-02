package project.keyappsk.domain.store.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StoreImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // PK

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Store_id", nullable = false)
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
}
