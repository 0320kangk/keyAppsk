package project.keyappsk.domain.orders.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.cart.repository.CartRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.repository.MemberRepository;
import project.keyappsk.domain.orders.dto.ProductCartCountDto;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;
import project.keyappsk.domain.orders.repository.OrderRepository;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;
import project.keyappsk.domain.ordersProduct.repository.OrdersProductRepository;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.store.entity.Store;
import project.keyappsk.domain.store.repository.StoreRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrdersProductRepository ordersProductRepository;

    @Transactional
    public void createOrder(Member member, Integer storeId) {
        List<ProductCartCountDto> productCartCountDtos = cartRepository.findProductInCart(member.getId(), storeId);
        ArrayList<Product> products = new ArrayList<>();
        productCartCountDtos.forEach(productCartCountDto -> {
            log.info("product : {}", productCartCountDto.getProduct());
            products.add(productCartCountDto.getProduct());
        });

        Order order = Order.builder()
                .memberBuyer(member)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .ordersStatus(OrdersStatus.WAITING)
                .build();
        for (ProductCartCountDto productCartCountDto : productCartCountDtos) {
            products.add(productCartCountDto.getProduct());
            OrdersProduct ordersProduct = OrdersProduct.builder()
                    .order(order)
                        .product(productCartCountDto.getProduct())
                        .count(productCartCountDto.getCount())
                        .build();
                ordersProductRepository.save(ordersProduct);
        }
        orderRepository.save(order);
        //carts
    }
}
