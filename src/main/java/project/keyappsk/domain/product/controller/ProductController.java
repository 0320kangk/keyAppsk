package project.keyappsk.domain.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.keyappsk.domain.category.service.CategoryService;
import project.keyappsk.domain.product.dto.ProductAddFormDto;
import project.keyappsk.domain.product.service.ProductService;
import project.keyappsk.domain.store.service.StoreService;

import java.io.IOException;
import java.net.MalformedURLException;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    @GetMapping("/product/add/{categoryId}")
    String getProductAddForm(@PathVariable("categoryId") Integer categoryId,
                             @ModelAttribute("productAddFormDto") ProductAddFormDto productAddFormDto,
                             Model model) {
        model.addAttribute("categoryId", categoryId);

        return "content/productAddForm";
    }
    @PostMapping("/product/add/{categoryId}")
    String postProductAddForm(@PathVariable("categoryId") Integer categoryId,
                              @ModelAttribute("productAddFormDto") ProductAddFormDto productAddFormDto
                              ) throws IOException {
        productService.storeAddFormDtoSave(productAddFormDto, categoryId);
        Integer storeId = categoryService.findStoreById(categoryId);
        return "redirect:/store/myStore/" + storeId;

    }
    @ResponseBody
    @GetMapping("/product/image/{filename}")
    public Resource downloadImage(@PathVariable("filename") String filename) throws
            MalformedURLException {
        log.info("get Store Image controller contact");
        return new UrlResource("file:" + productService.getFullPath(filename));
    }

}
