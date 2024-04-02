package project.keyappsk.domain.store.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.store.dto.StoreAddFormDto;
import project.keyappsk.domain.store.entity.Store;
import project.keyappsk.domain.store.service.StoreService;

import java.io.IOException;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class StoreController {
    private final StoreService storeService;
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
        return "content/myInfo";
    }

    @GetMapping("/store/search")
    String getStores(){
        return "content/stores";
    }

    @GetMapping("/store/myStores")
    String getMyStores(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        List<Store> stores = storeService.getStores(customUserDetails.getMember());

        return "content/myStores";
    }
}
