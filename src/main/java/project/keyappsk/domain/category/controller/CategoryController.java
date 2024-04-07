package project.keyappsk.domain.category.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.keyappsk.domain.category.dto.CategoryAddFormDto;
import project.keyappsk.domain.category.service.CategoryService;

import java.net.http.HttpResponse;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/category/add")
    public String postCategoryAddForm(@Validated @ModelAttribute("categoryAddFormDto") CategoryAddFormDto categoryAddFormDto,
                                      BindingResult bindingResult,
                                      @ModelAttribute("storeName") String storeName,
                                      RedirectAttributes redirectAttributes){
        log.info("contact:post category add");
        log.info("storeName : {}", storeName);
        if (bindingResult.hasErrors()){
            log.info("hasError");
            log.info("error: {}", bindingResult.getFieldError());
            return "content/myPage/myStore";
        }
        categoryService.saveCategoryAddFormDto(storeName,categoryAddFormDto);
        redirectAttributes.addAttribute("storeName", storeName);
        return "redirect:/store/myStore";
    }

}
