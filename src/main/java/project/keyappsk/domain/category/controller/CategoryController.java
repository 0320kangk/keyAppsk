package project.keyappsk.domain.category.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.keyappsk.domain.category.dto.CategoryAddFormDto;
import project.keyappsk.domain.category.service.CategoryService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/category/add/{storeId}")
    public String postCategoryAddForm(@Validated @ModelAttribute("categoryAddFormDto") CategoryAddFormDto categoryAddFormDto,
                                      BindingResult bindingResult,
                                      @PathVariable("storeId") Integer storeId
                                     ){
        log.info("contact:post category add");
        log.info("storeId : {}", storeId);
        if (bindingResult.hasErrors()){
            log.info("hasError");
            log.info("error: {}", bindingResult.getFieldError());
            return "content/myPage/myStore";
        }
        categoryService.saveCategoryAddFormDto(storeId,categoryAddFormDto);
//        redirectAttributes.addAttribute("storeId", storeId);
        return "redirect:/store/myStore/" + storeId;
    }

}
