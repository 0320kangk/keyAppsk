package project.keyappsk.domain.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.keyappsk.domain.category.service.CategoryService;
import project.keyappsk.domain.product.dto.ProductAddFormDto;
import project.keyappsk.domain.product.dto.ProductDetailFormDto;
import project.keyappsk.domain.product.dto.ProductUpdateFormDto;
import project.keyappsk.domain.product.service.ProductService;

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

        return "content/product/productAddForm";
    }
    @PostMapping("/product/add/{categoryId}")
    String postProductAddForm(@PathVariable("categoryId") Integer categoryId,
                              @ModelAttribute("productAddFormDto") ProductAddFormDto productAddFormDto
                              ) throws IOException {
        productService.storeAddFormDtoSave(productAddFormDto, categoryId);
        Integer storeId = categoryService.findStoreById(categoryId);
        return "redirect:/store/myStore/" + storeId;

    }
    @PostMapping("/product/update/{productId}")
    String postProductUpdateForm(@PathVariable("productId") Integer productId,
                              @Validated @ModelAttribute("productUpdateFormDto") ProductUpdateFormDto productUpdateFormDto,
                                 BindingResult bindingResult
    ) throws IOException {
        log.info("productUpdateFormDto {}", productUpdateFormDto);
        productService.updateProduct(productUpdateFormDto, productId);
        Integer storeId = productService.findStoreId(productId);
        if(bindingResult.hasErrors()){
            return "redirect:/store/myStore/" + storeId;
        }
//        productService.storeAddFormDtoSave(productAddFormDto, categoryId);
//        Integer storeId = categoryService.findStoreById(categoryId);
        return "redirect:/store/myStore/" + storeId;

    }
    @GetMapping("/product/detail/{productId}")
    public String getProductDetail(@PathVariable("productId") Integer productId,
                                   Model model){
        ProductDetailFormDto byIdProductDetailFormDto = productService.findByIdProductDetailFormDto(productId);
        model.addAttribute("productDetailFormDto", byIdProductDetailFormDto);
        return "content/product/productDetail";
    }

    @ResponseBody
    @GetMapping("/product/image/{filename}")
    public Resource downloadImage(@PathVariable("filename") String filename) throws
            MalformedURLException {
        log.info("get Store Image controller contact");
        return new UrlResource("file:" + productService.getFullPath(filename));
    }

}
