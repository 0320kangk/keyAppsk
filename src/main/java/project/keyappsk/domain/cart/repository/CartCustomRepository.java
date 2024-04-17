package project.keyappsk.domain.cart.repository;

import com.querydsl.core.Tuple;
import project.keyappsk.domain.orders.dto.ProductCartCountDto;
import project.keyappsk.domain.product.entity.Product;

import java.util.List;

public interface CartCustomRepository {
    public List<ProductCartCountDto> findProductInCart(Integer memberId, Integer storeId);
}
