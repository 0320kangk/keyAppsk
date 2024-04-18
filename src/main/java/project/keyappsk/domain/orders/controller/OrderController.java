package project.keyappsk.domain.orders.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.orders.service.OrderService;

@Controller
@Slf4j
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/order")
    public String getOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails) {
        //meber의 모든 orders 안의 store,store id 갖기
        //orders 안의 모든 product, store id를 갖는다.

        return "index";
    }
    @PostMapping("/order")
    public String postOrder(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                            @RequestParam("storeId") Integer storeId) {
        log.info("storeId: {}",storeId);
        orderService.createOrder(customUserDetails.getMember(), storeId);

        return "index";
    }
}
