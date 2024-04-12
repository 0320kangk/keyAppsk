package project.keyappsk.domain.cart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.cart.repository.CartRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.repository.ProductRepository;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    @Transactional
    public void addCart(Integer productId,Integer count ,Member member){
        Product product = productRepository.findById(productId).orElseThrow(() -> new IllegalArgumentException());
        Cart cart = Cart.builder()
                .productCount(count)
                .product(product)
                .store(product.getStore())
                .member(member)
                .build();
        cartRepository.save(cart);
    }


}

