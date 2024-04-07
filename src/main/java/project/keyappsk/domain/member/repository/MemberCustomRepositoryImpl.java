package project.keyappsk.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.member.entity.QMember;
import project.keyappsk.domain.store.dto.MemberStoreDto;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements  MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<MemberStoreDto> getMemberStoreDto(Integer memberId,Pageable pageable) {
        QMember member = QMember.member;
        QStore store = QStore.store;
        List<MemberStoreDto> content = jpaQueryFactory
                .select(Projections.constructor(
                        MemberStoreDto.class,
                        store.id,
                        store.name,
                        store.storeImage.uploadFileName,
                        store.storeImage.storeFileName,
                        store.storeStatus,
                        store.roadAddress,
                        store.jibunAddress,
                        store.detailAddress,
                        store.extraAddress
                ))
                .from(member)
                .join(member.stores, store)
                .on(member.id.eq(memberId))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(content, pageable, content.size());

    }


}
