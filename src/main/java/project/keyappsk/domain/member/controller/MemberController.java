package project.keyappsk.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import project.keyappsk.domain.member.dto.LoginMemberDto;
import project.keyappsk.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/member/add")
    String getMemberAdd(@ModelAttribute("loginMemberDto") LoginMemberDto loginMemberDto){
        return "content/sign";
    }
    @PostMapping("/member/add")
    String postMemberAdd(@Valid @ModelAttribute LoginMemberDto loginMemberDto){
        memberService.save(loginMemberDto);
        return "content/sign";
    }
    @GetMapping("/member/login")
    String getLoginPage(){
        return "content/login";
    }

    @GetMapping("/member/info")
    String getMemberInfoPage(){return "content/memberInfo";}

}
