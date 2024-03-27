package project.keyappsk.domain.cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {

    @GetMapping("/cart")
    String getCartPage(){
        return "/content/cart";
    }
}
