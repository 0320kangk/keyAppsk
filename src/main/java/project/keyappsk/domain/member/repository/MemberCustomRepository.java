package project.keyappsk.domain.member.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.cart.dto.CartStoreDto;
import project.keyappsk.domain.cart.dto.CartStoreProductDto;
import project.keyappsk.domain.store.dto.MemberStoreDto;

import java.util.List;


public interface MemberCustomRepository {

    Page<MemberStoreDto> getMemberStoreDto(Integer memberId, Pageable pageable);

    List<CartStoreDto> getCartStoreDto(Integer memberId);
    List<CartStoreProductDto> getCartStoreProductDto(Integer memberId);
}
