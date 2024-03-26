package project.keyappsk.domain.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemberController {
    @GetMapping("/member/add")
    String getMemberPage(){
        return "/content/sign";
    }


}
