package project.keyappsk.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
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
import project.keyappsk.domain.category.service.CategoryService;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.page.dto.PageDto;
import project.keyappsk.domain.page.service.PageService;
import project.keyappsk.domain.product.dto.ProductMyStoreDto;
import project.keyappsk.domain.product.dto.ProductUpdateFormDto;
import project.keyappsk.domain.product.service.ProductService;
import project.keyappsk.domain.store.dto.*;
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
    private final PageService pageService;
    @GetMapping("/store/add")
    String getStoreAddForm(@ModelAttribute StoreAddFormDto storeAddFormDto){
        return "content/store/storeAddForm";
    }
    @PostMapping("/store/add")
    String postStoreAdd(@AuthenticationPrincipal CustomUserDetails customUserDetails,
            @Validated @ModelAttribute("storeAddFormDto") StoreAddFormDto StoreAddFormDto,
                        BindingResult bindingResult) throws IOException {

        if(bindingResult.hasErrors()){
            return "content/store/storeAddForm";
        }
        storeService.storeAddFormDtoSave(StoreAddFormDto, customUserDetails.getMember());
        return "redirect:/store/myStores";
    }
    @GetMapping("/store/myStores")
    String getMyStores(@AuthenticationPrincipal CustomUserDetails customUserDetails,
                       @PageableDefault(size=9, direction = Sort.Direction.DESC) Pageable pageable,
                       Model model){
        Page<MemberStoreDto> storesPage = storeService.getStores(customUserDetails.getMember(), pageable);
        List<MemberStoreDto> stores = storesPage.getContent();
        log.info("stores size: {}", stores.size());
        log.info("presentPage {}", storesPage.getNumber());
        log.info("totalPages {}", storesPage.getTotalPages());


        PageDto pageDto = pageService.get10unitPageInit(storesPage.getNumber(), storesPage.getTotalPages());
        model.addAttribute("pageDto", pageDto);
        model.addAttribute ("stores", stores);
        return "content/myPage/myStores";
    }
    @GetMapping("/store/myStore/{storeId}")
    String getMyStore(@PathVariable("storeId") Integer storeId,
                        @ModelAttribute("categoryAddFormDto") CategoryAddFormDto categoryAddFormDto,
                      @ModelAttribute("productUpdateFormDto") ProductUpdateFormDto productUpdateFormDto,
                      @ModelAttribute("storeName") String storeName,
                      Model model) {
        List<CategoryStoreDto> categorys = categoryService.getCategoryStoreDto(storeId);
        List<ProductMyStoreDto> productMyStoreDto = productService.getProductMyStoreDto(storeId);

        model.addAttribute("storeId",storeId);
        model.addAttribute("categorys", categorys);
        model.addAttribute("productMyStoreDtos", productMyStoreDto);
        return "content/myPage/myStore";
    }
    @GetMapping("/store/myStore/{storeId}/update")
    public String getStoreUpdateForm(@PathVariable("storeId") Integer storeId ,
                                     Model model){
        StoreUpdateFormDto storeUpdateFormDto = storeService.storeIdToStoreUpdateFormDto(storeId);
        model.addAttribute("storeId", storeId);
        model.addAttribute("storeUpdateFormDto",storeUpdateFormDto);
        return "content/store/storeUpdateForm";
    }
    @PostMapping("/store/{storeId}/update")
    public String postStoreUpdateForm(@PathVariable("storeId") Integer storeId,
                                      @Validated @ModelAttribute("storeUpdateFormDto") StoreUpdateFormDto storeUpdateFormDto,
                                      BindingResult bindingResult) throws IOException {
      /*  if(bindingResult.hasErrors()){
            return "redirect:/store/myStore/" +  storeId + "/update";
        }*/
        storeService.updateStore(storeId, storeUpdateFormDto);
        return "redirect:/store/myStores";
    }
    @GetMapping("/store/{storeId}")
    String getStore(@PathVariable("storeId") Integer storeId,
                      @PageableDefault(size = 9) Pageable pageable,
                      Model model) {
        List<CategoryStoreDto> categorys = categoryService.getCategoryStoreDto(storeId);
        Page<StoreProductDto> storeProductDtos = productService.getStoreProductDto(storeId, pageable);
        PageDto pageDto = pageService.get10unitPageInit(storeProductDtos.getNumber(), storeProductDtos.getTotalPages());
        model.addAttribute("pageDto",pageDto);
        model.addAttribute("storeId",storeId);
        model.addAttribute("categorys", categorys);
        model.addAttribute("storeProductDtos", storeProductDtos);
        return "content/store/store";
    }
    @GetMapping("/store/search")
    public String getStoreSearch(@ModelAttribute("query")  String query,
                                 @PageableDefault(size=9, direction = Sort.Direction.DESC) Pageable pageable
                                 , Model model) {
        Page<StoreSearchDto> storeSearchPageDtos = storeService.searchStorePagination(query, pageable);
        List<StoreSearchDto> storeSearchDtos = storeSearchPageDtos.getContent();
        log.info("storeSearchDtos: size {}", storeSearchDtos.size());
        log.info("presentPage {}", storeSearchPageDtos.getNumber());
        log.info("totalPages {}", storeSearchPageDtos.getTotalPages());
        /*

         */
        PageDto pageDto = pageService.get10unitPageInit(storeSearchPageDtos.getNumber(), storeSearchPageDtos.getTotalPages());
        model.addAttribute("pageDto",pageDto);
        model.addAttribute("storeSearchDtos",storeSearchDtos);
        return "content/store/storeSearchForm";
    }

    @ResponseBody
    @GetMapping("/store/image/{filename}")
    public Resource downloadImage(@PathVariable("filename") String filename) throws
            MalformedURLException {
//        log.info("get Store Image controller contact");
        return new UrlResource("file:" + storeService.getFullPath(filename));
    }

}
