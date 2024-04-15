package project.keyappsk.domain.cart.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CartStoreDto {
    @NotNull
    Integer id;
    @NotNull
    String name;
    @NotNull
    StoreStatus status;
    @NotBlank
    String roadAddress;
    @NotBlank
    String jibunAddress;
    @NotBlank
    String detailAddress;
    @NotBlank
    String extraAddress;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartStoreDto that = (CartStoreDto) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, roadAddress, jibunAddress, detailAddress, extraAddress);
    }
}
