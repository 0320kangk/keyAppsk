package project.keyappsk.domain.product.dto;

import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import project.keyappsk.domain.store.entity.Store;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailsStoreDto {
    @NotBlank
    private String name;

    @NotBlank
    private String roadAddress;

    @NotBlank
    private String jibunAddress;

    @NotBlank
    private String detailAddress;

    @NotBlank
    private String extraAddress;

    @QueryProjection
    public ProductDetailsStoreDto(Store store){
        this.name = store.getName();
        this.roadAddress = store.getRoadAddress();
        this.jibunAddress = store.getJibunAddress();
        this.detailAddress = store.getDetailAddress();
        this.extraAddress = store.getExtraAddress();
    }
}
