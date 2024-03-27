package project.keyappsk.domain.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class storeController {
    @GetMapping("/store/add")
    String getAddStorePage(){

        return "/content/addStore";
    }
}
