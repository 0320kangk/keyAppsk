package project.keyappsk.domain.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {
    @GetMapping("/product/add")
    String getProductAddForm() {

        return "content/productAddForm";
    }
}
