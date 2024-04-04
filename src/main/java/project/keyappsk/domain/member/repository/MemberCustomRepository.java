package project.keyappsk.domain.member.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import project.keyappsk.domain.store.dto.MemberStoreDto;


public interface MemberCustomRepository {

    Page<MemberStoreDto> getMemberStoreDto(int memberId, Pageable pageable);
}
