package project.keyappsk.domain.orders.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.orders.dto.OrderStoreDto;
import project.keyappsk.domain.orders.dto.OrderStoreProductDto;
import project.keyappsk.domain.orders.service.OrderService;

import java.util.Set;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public String getOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                           @PageableDefault(size=9, direction = Sort.Direction.DESC) Pageable pageable,
                           Model model) {
        //meber의 모든 orders 안의 store,store id 갖기
        //orders 안의 모든 product, store id를 갖는다.
        Page<OrderStoreProductDto> orderStoreProductDtos = orderService.getMyOrderStoreProductDto(customUserDetails.getMember().getId(), pageable);
        Set<OrderStoreDto> orderStoreDtos = orderService.getOrderStoreDto(orderStoreProductDtos.getContent());

        log.info("orderStoreProductDto {}", orderStoreProductDtos.getContent());
        log.info("orderStoreDto {}", orderStoreDtos);
        model.addAttribute("orderStoreProductDtos" , orderStoreProductDtos);
        model.addAttribute("orderStoreDtos", orderStoreDtos);
        return "content/order/myOrder";
    }
    @GetMapping("/order/store")
    public String getOrderStore(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                                @PageableDefault(size=9, direction = Sort.Direction.DESC) Pageable pageable,
                                Model model){
        Page<OrderStoreProductDto> orderStoreProductDtos = orderService.getMyOrderedStoreProductDto(customUserDetails.getMember().getId(), pageable);
        Set<OrderStoreDto> orderStoreDtos = orderService.getOrderStoreDto(orderStoreProductDtos.getContent());

        log.info("orderStoreProductDto {}", orderStoreProductDtos.getContent());
        log.info("orderStoreDto {}", orderStoreDtos);
        model.addAttribute("orderStoreProductDtos" , orderStoreProductDtos);
        model.addAttribute("orderStoreDtos", orderStoreDtos);
        return "content/order/myOrdered";
    }

    @PostMapping("/order")
    public String postOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                            @RequestParam("storeId") Integer storeId) {
        log.info("storeId: {}",storeId);
        orderService.createOrder(customUserDetails.getMember(), storeId);

        return "index";
    }
}
