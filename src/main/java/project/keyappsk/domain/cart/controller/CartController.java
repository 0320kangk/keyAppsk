package project.keyappsk.domain.cart.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.keyappsk.domain.cart.dto.CartAddDto;
import project.keyappsk.domain.cart.service.CartService;
import project.keyappsk.domain.member.dto.CustomUserDetails;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @GetMapping("/cart")
    public String getCartForm(@AuthenticationPrincipal CustomUserDetails customUserDetails){

        return "content/cart";
    }
    @PostMapping("/cart/add")
    public String postCartAdd(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @ModelAttribute CartAddDto cartAddDto){
        cartService.addCart(cartAddDto.getProductId(), cartAddDto.getProductCount(), customUserDetails.getMember());

        return "content/cart";
    }

}
