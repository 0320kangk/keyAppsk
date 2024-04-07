package project.keyappsk.domain.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import project.keyappsk.domain.product.dto.ProductAddFormDto;

@Controller
public class ProductController {
    @GetMapping("/product/add/{categoryId}")
    String getProductAddForm(@PathVariable("categoryId") Integer categoryId,
                             @ModelAttribute("productAddFormDto") ProductAddFormDto productAddFormDto,
                             Model model) {
        model.addAttribute("categoryId", categoryId);

        return "content/productAddForm";
    }
    @PostMapping("/product/add/{categoryId}")
    String postProductAddForm(@PathVariable("categoryId") Integer categoryId,
                              @ModelAttribute("productAddFormDto") ProductAddFormDto productAddFormDto){


        return "test";

    }

}
