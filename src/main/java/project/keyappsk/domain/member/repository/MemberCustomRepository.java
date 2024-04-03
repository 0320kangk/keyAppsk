package project.keyappsk.domain.member.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.store.dto.MemberStoreDto;

@Repository
public interface MemberCustomRepository {
    Page<MemberStoreDto> getMemberStoreDto(Pageable pageable);
}
