package project.keyappsk.domain.member.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import project.keyappsk.domain.cart.dto.CartStoreDto;
import project.keyappsk.domain.cart.dto.CartStoreProductDto;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.cart.entity.QCart;
import project.keyappsk.domain.member.entity.QMember;
import project.keyappsk.domain.product.entity.QProduct;
import project.keyappsk.domain.store.dto.MemberStoreDto;
import project.keyappsk.domain.store.entity.QStore;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberCustomRepositoryImpl implements  MemberCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public List<CartStoreProductDto> getCartStoreProductDto(Integer memberId){
        QMember member = QMember.member;
        QCart cart = QCart.cart;
        QProduct product = QProduct.product;
        List<CartStoreProductDto> fetch = jpaQueryFactory
                .select(Projections.constructor(
                        CartStoreProductDto.class,
                        product.id,
                        product.name,
                        product.store.id,
                        product.price,
                        product.count,
                        product.productStatus,
                        cart.id,
                        cart.productCount,
                        product.productImage.storeFileName,
                        product.productImage.uploadFileName
                ))
                .from(member)
                .join(member.carts, cart)
                .join(cart.product, product)
                .fetch();
        return fetch;
    }

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

    @Override
    public List<CartStoreDto> getCartStoreDto(Integer memberId){
        QMember member = QMember.member;
        QStore store = QStore.store;
        QCart cart = QCart.cart;
        List<CartStoreDto> fetch = jpaQueryFactory
                .select(Projections.constructor(
                        CartStoreDto.class,
                        store.id,
                        store.name,
                        store.storeStatus,
                        store.roadAddress,
                        store.jibunAddress,
                        store.detailAddress,
                        store.extraAddress
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .join(member.carts, cart)
                .join(cart.store, store)
                .fetch();
        return fetch;
    }


}
