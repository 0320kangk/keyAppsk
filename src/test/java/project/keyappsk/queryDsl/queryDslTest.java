package project.keyappsk.queryDsl;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.repository.CategoryRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.entity.QMember;
import project.keyappsk.domain.member.repository.MemberCustomRepositoryImpl;
import project.keyappsk.domain.member.repository.MemberRepository;
import project.keyappsk.domain.store.dto.MemberStoreDto;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@RequiredArgsConstructor
@Slf4j
//@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
public class queryDslTest {

    /*
    Fetch
    애플리케이션이 DB로부터 데이터를 가지고 오는 것
    DB와 통신하여 데이터를 읽는 것에는 큰 비용이 소모되기 때문에 이를 해결하는 전략
     */
    /*
    김영한 :JPAQueryFactory는 select 절 부터 적을 수 있게 도와줍니다^^ 반면에 JPAQuery는 그렇지 못하지요.
     */

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private MemberCustomRepositoryImpl memberCustomRepositoryImpl;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @DisplayName("QeuryDsl, member와 연관된 모든 store 조회하기")
    @Transactional
    @Test
    void MemberLeftJoinStore() {

        QMember m = new QMember("m");
        QStore s = new QStore("s");

        List<Member> fetch = jpaQueryFactory.selectFrom(m)
                .leftJoin(m.stores, s)
                .fetch();

        for (Member member : fetch) {
            log.info("{}",member);
            log.info("{}", member.getStores());
        }
        assertThat(fetch).hasSize(1);
    }
    @DisplayName("Projection을 이용한 조회")
    @Transactional
    @Test
    void projectionTest(){
        List<testShopDto> fetch = jpaQueryFactory.select(Projections.constructor(testShopDto.class, QStore.store.name, QStore.store.roadAddress, QMember.member.email))
                .from(QMember.member)
                .leftJoin(QStore.store)
                .on(QMember.member.id.eq(QStore.store.id))
                .fetch();
        assertThat(fetch).hasSize(1);
        System.out.println(fetch);
    }

    @DisplayName("pageable test")
    @Transactional
    @Test
    void projectionsTest(){
        PageRequest pageRequest = PageRequest.of(0,1);
        Page<MemberStoreDto> memberStoreDto = memberRepository.getMemberStoreDto(1,pageRequest);
        List<MemberStoreDto> content = memberStoreDto.getContent();
        System.out.println(content);
    }

    @DisplayName("Category join store on id test")
    @Transactional
    @Test
    void selectCategoryJoinStore(){
        List<CategoryStoreDto> categoryJoinStoreOnId = categoryRepository.findCategoryJoinStoreOnName("강준호");
        for (CategoryStoreDto categoryStoreDto : categoryJoinStoreOnId) {
            System.out.println(categoryStoreDto.getName());
        }
        assertThat(categoryJoinStoreOnId).hasSize(1);
    }


}
