package project.keyappsk.domain.cart.controller;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import project.keyappsk.domain.cart.dto.CartAddDto;
import project.keyappsk.domain.cart.dto.CartStoreDto;
import project.keyappsk.domain.cart.dto.CartStoreProductDto;
import project.keyappsk.domain.cart.service.CartService;
import project.keyappsk.domain.member.dto.CustomUserDetails;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CartController {

    private final CartService cartService;
    @GetMapping("/cart")
    public String getCartForm(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              Model model){
        List<CartStoreDto> cartStoreDtos = cartService.findDistinctCartStoreDtoByMemberId(customUserDetails.getMember().getId());
        List<CartStoreProductDto> cartStoreProductDto = cartService.findCartStoreProductDto(customUserDetails.getMember().getId());
        log.info("cartStoreDto {}",cartStoreDtos);
        log.info("CartStoreProductDto {}", cartStoreProductDto);
        model.addAttribute("cartStoreDtos", cartStoreDtos);
        model.addAttribute("cartStoreProductDtos", cartStoreProductDto);
        return "content/cart";
    }
    @PostMapping("/cart/add")
    public String postCartAdd(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @ModelAttribute CartAddDto cartAddDto){
        cartService.addCart(cartAddDto.getProductId(), cartAddDto.getProductCount(), customUserDetails.getMember());

        return "redirect:/cart";
    }

}
