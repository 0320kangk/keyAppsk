package project.keyappsk.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import project.keyappsk.domain.store.entity.enumerate.StoreStatus;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class MemberStoreDto {

    @NotBlank
    private String name;

    @NotBlank
    private String uploadFileName;  //회원이 올린 파일 이름

    @NotBlank
    private String storeFileName;   //실제 저장 될 파일 이름

    @NotBlank
    private StoreStatus storeStatus;

    @NotBlank
    private String roadAddress;

    @NotBlank
    private String jibunAddress;

    @NotBlank
    private String detailAddress;

    @NotBlank
    private String extraAddress;


}
