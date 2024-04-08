package project.keyappsk.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import project.keyappsk.domain.category.dto.CategoryAddFormDto;
import project.keyappsk.domain.category.dto.CategoryStoreDto;
import project.keyappsk.domain.category.repository.CategoryRepository;
import project.keyappsk.domain.category.service.CategoryService;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.service.ProductService;
import project.keyappsk.domain.store.dto.MemberStoreDto;
import project.keyappsk.domain.store.dto.StoreAddFormDto;
import project.keyappsk.domain.store.service.StoreService;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private final StoreService storeService;
    private final CategoryService categoryService;
    private final ProductService productService;
    @GetMapping("/store/add")
    String getStoreAddForm(@ModelAttribute StoreAddFormDto storeAddFormDto){
        return "content/storeAddForm";
    }
    @PostMapping("/store/add")
    String postStoreAdd(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @ModelAttribute("storeAddFormDto") StoreAddFormDto StoreAddFormDto,
                        BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            return "content/storeAddForm";
        }
        storeService.storeAddFormDtoSave(StoreAddFormDto, customUserDetails.getMember());
        return "content/myPage/myInfo";
    }

    @GetMapping("/store/search")
    String getStores(){
        return "content/stores";
    }

    @GetMapping("/store/myStores")
    String getMyStores(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @PageableDefault(size=5, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        List<MemberStoreDto> stores = storeService.getStores(customUserDetails.getMember(), pageable);
        log.info("stores size: {}", stores.size());
        model.addAttribute ("stores", stores);
        model.addAttribute("page", pageable.getPageNumber());

        return "content/myPage/myStores";
    }

    @GetMapping("/store/myStore/{storeId}")
    String getMyStore(@PathVariable("storeId") Integer storeId,
                        @ModelAttribute("categoryAddFormDto") CategoryAddFormDto categoryAddFormDto,
                      @ModelAttribute("storeName") String storeName,
                      Model model) {
        List<CategoryStoreDto> categorys = categoryService.getCategoryStoreDto(storeId);
        List<ProductMyStoreDto> productMyStoreDto = productService.getProductMyStoreDto(storeId);
        model.addAttribute("storeId",storeId);
        model.addAttribute("categorys", categorys);
        model.addAttribute("productMyStoreDtos", productMyStoreDto);
        return "content/myPage/myStore";
    }
    @ResponseBody
    @GetMapping("/store/image/{filename}")
    public Resource downloadImage(@PathVariable("filename") String filename) throws
            MalformedURLException {
        log.info("get Store Image controller contact");
        return new UrlResource("file:" + storeService.getFullPath(filename));
    }

}
