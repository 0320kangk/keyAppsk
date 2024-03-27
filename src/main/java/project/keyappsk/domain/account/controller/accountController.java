package project.keyappsk.domain.account.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class accountController {
    @GetMapping("/accountInfo")
    String getAccountInfoPage(){
        return "/content/accountInfo";
    }
}
