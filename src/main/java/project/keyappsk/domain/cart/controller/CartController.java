package project.keyappsk.domain.cart.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.keyappsk.domain.cart.dto.CartAddDto;
import project.keyappsk.domain.cart.dto.CartStoreDto;
import project.keyappsk.domain.cart.dto.CartStoreProductDto;
import project.keyappsk.domain.cart.service.CartService;
import project.keyappsk.domain.member.dto.CustomUserDetails;

import java.util.List;
import java.util.Map;

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

    @PostMapping("/cart/{cartId}/update/count")
    @ResponseBody
    public ResponseEntity<String> cartCountUpdate(@PathVariable("cartId") Integer cartId,
                                                  @RequestBody Map<String, Object>  requestData ) {
        Integer count = (Integer) requestData.get("count");
        if( cartId == null || count == null) {
            String errorMessage = "Failed to process the request";
            // HTTP 응답 상태 코드와 함께 실패 메시지를 반환합니다.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
        }
        String successMessage = "cart update success";
        cartService.updateCartCount(cartId, count);

        return ResponseEntity.status(HttpStatus.OK).body(successMessage);
    }

    @PostMapping("/cart/add")
    public String postCartAdd(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                              @ModelAttribute CartAddDto cartAddDto){
        cartService.addCart(cartAddDto.getProductId(), cartAddDto.getProductCount(), customUserDetails.getMember());

        return "redirect:/cart";
    }

}
