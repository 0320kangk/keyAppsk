package project.keyappsk.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StoreSearchDto {

    @NotNull
    private Integer id;
    @NotBlank
    private String name;
    @NotBlank
    private StoreStatus status;
    @NotBlank
    private String roadAddress;
    @NotBlank
    private String jibunAddress;
    @NotBlank
    private String storeFileName;   //실제 저장 될 파일 이름
}
