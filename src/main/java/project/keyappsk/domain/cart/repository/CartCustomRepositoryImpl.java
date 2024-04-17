package project.keyappsk.domain.cart.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import project.keyappsk.domain.cart.entity.QCart;
import project.keyappsk.domain.orders.dto.ProductCartCountDto;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.entity.QProduct;

import java.util.List;

@RequiredArgsConstructor
public class CartCustomRepositoryImpl implements  CartCustomRepository   {
    private final JPAQueryFactory jpaQueryFactory;
    @Override
    public List<ProductCartCountDto> findProductInCart(Integer memberId, Integer storeId) {
        QCart cart = QCart.cart;
        QProduct product = QProduct.product;
        List<ProductCartCountDto> fetch = jpaQueryFactory
                .select(Projections.constructor(ProductCartCountDto.class, product, cart.productCount))
                .from(cart)
                .join(product).on(cart.product.id.eq(product.id))
                .where(cart.store.id.eq(storeId).and(cart.member.id.eq(memberId)))
                .fetch();
        return fetch;
    }
}
