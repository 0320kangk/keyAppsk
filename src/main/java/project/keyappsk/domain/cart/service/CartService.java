package project.keyappsk.domain.cart.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project.keyappsk.domain.cart.dto.CartStoreDto;
import project.keyappsk.domain.cart.dto.CartStoreProductDto;
import project.keyappsk.domain.cart.entity.Cart;
import project.keyappsk.domain.cart.repository.CartRepository;
import project.keyappsk.domain.member.entity.Member;
import project.keyappsk.domain.member.repository.MemberRepository;
import project.keyappsk.domain.product.entity.Product;
import project.keyappsk.domain.product.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;


    @Transactional
    public void updateCartCount(Integer cartId, Integer count) {
        Cart cart = cartRepository.findById(cartId).orElseThrow(() -> new IllegalArgumentException());
        cart.setProductCount(count);
        cartRepository.save(cart);
    }

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
    //가게 이름, 가게 id
    @Transactional
    public List<CartStoreDto> findDistinctCartStoreDtoByMemberId(Integer memberId) {
        List<CartStoreDto> cartStoreDtos = memberRepository.getCartStoreDto(memberId);
        return cartStoreDtos.stream().distinct().toList();
    }
    @Transactional
    public List<CartStoreProductDto> findCartStoreProductDto(Integer memberId) {
        return memberRepository.getCartStoreProductDto(memberId);

    }


}

