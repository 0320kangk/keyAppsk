package project.keyappsk.domain.orders.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class OrderStoreDto {
    @NotNull
    Integer storeId;

    @NotBlank
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderStoreDto that = (OrderStoreDto) o;
        return Objects.equals(storeId, that.storeId) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeId, name);
    }
}
