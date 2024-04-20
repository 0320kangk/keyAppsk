package project.keyappsk.domain.orders.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import project.keyappsk.domain.cart.repository.CartRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.orders.dto.OrderStoreDto;
import project.keyappsk.domain.orders.dto.OrderStoreProductDto;
import project.keyappsk.domain.orders.dto.ProductCartCountDto;
import project.keyappsk.domain.orders.entity.Order;
import project.keyappsk.domain.orders.entity.enumerate.OrdersStatus;
import project.keyappsk.domain.orders.except.InsufficientStockException;
import project.keyappsk.domain.orders.repository.OrderRepository;
import project.keyappsk.domain.ordersProduct.entity.OrdersProduct;
import project.keyappsk.domain.ordersProduct.repository.OrdersProductRepository;
import project.keyappsk.domain.product.entity.Product;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrdersProductRepository ordersProductRepository;

    @Transactional
    public void completeOrder(Integer orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException());
        order.setOrdersStatus(OrdersStatus.COMPLETE);
        orderRepository.save(order);
    }

    @Transactional
    public void prepareOrder(Integer orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException());
        order.setOrdersStatus(OrdersStatus.PREPARATION);
        orderRepository.save(order);
    }



    @Transactional
    public void cancelOrder(Integer orderId){
        Order order = orderRepository.findById(orderId).orElseThrow(() -> new IllegalArgumentException());
        order.setOrdersStatus(OrdersStatus.CANCEL);
        orderRepository.save(order);
    }
    @Transactional
    public void createOrder(Member member, Integer storeId) {
        List<ProductCartCountDto> productCartCountDtos = cartRepository.findProductInCart(member.getId(), storeId);
        Order order = Order.builder()
                .memberBuyer(member)
                .createdDate(LocalDateTime.now())
                .updatedDate(LocalDateTime.now())
                .ordersStatus(OrdersStatus.WAITING)
                .build();
        for (ProductCartCountDto productCartCountDto : productCartCountDtos) {
            Product product = productCartCountDto.getProduct();
            if (productCartCountDto.getCount() > product.getCount()){
                throw new InsufficientStockException("Insufficient stock for product: " + product.getName());
            } else {
                product.setCount(product.getCount() - productCartCountDto.getCount());
            }
            OrdersProduct ordersProduct = OrdersProduct.builder()
                    .order(order)
                        .product(product)
                        .count(productCartCountDto.getCount())
                        .build();
                ordersProductRepository.save(ordersProduct);
        }
        orderRepository.save(order);
    }
    @Transactional
    public Page<OrderStoreProductDto> getMyOrderStoreProductDto(Integer memberId, Pageable pageable){
        return orderRepository.findMyOrderStoreProductDto(memberId, pageable);
    }
    @Transactional
    public Page<OrderStoreProductDto> getMyOrderedStoreProductDto(Integer memberId, Pageable pageable){
        return orderRepository.findMyOrderedStoreProductDto(memberId, pageable);
    }
    public Set<OrderStoreDto> getOrderStoreDto(List<OrderStoreProductDto> orderStoreProductDtos){
        Set<OrderStoreDto> orderStoreDtos = new HashSet<>();
        orderStoreProductDtos.forEach( (orderStoreProductDto -> {
            orderStoreDtos.add(OrderStoreDto.builder()
                            .storeId(orderStoreProductDto.getOrderId())
                            .name(orderStoreProductDto.getStoreName())
                    .build());
        }) );
        return orderStoreDtos;
    }

}