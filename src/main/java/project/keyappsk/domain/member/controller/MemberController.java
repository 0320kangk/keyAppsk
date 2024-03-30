package project.keyappsk.domain.member.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project.keyappsk.domain.member.dto.AddMemberFormDto;
import project.keyappsk.domain.member.dto.CustomUserDetails;
import project.keyappsk.domain.member.service.MemberService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;
    @GetMapping("/member/add")
    String getMemberAdd(@ModelAttribute("addMemberFormDto") AddMemberFormDto addMemberFormDto){
        return "content/addMemberForm";
    }
    @PostMapping("/member/add")
    String postMemberAdd(@Validated @ModelAttribute("addMemberFormDto") AddMemberFormDto addMemberFormDto,
                         BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            log.info("errors={}", bindingResult.getFieldError());
            log.info("errors={}", bindingResult.getAllErrors());
            return "content/addMemberForm";
        }
        memberService.save(addMemberFormDto);
        return "content/loginMemberForm";
    }
    @GetMapping("/member/login")
    String getLoginPage(@RequestParam(name = "error", required = false) String error,
                        @RequestParam(name= "errorMessage", required = false) String errorMessage,
                        Model model){

        model.addAttribute ("error", error);
        model.addAttribute("errorMessage", errorMessage);

        return "content/loginMemberForm";
    }

    @GetMapping("/member/info")
    String getMemberInfoPage(){return "content/memberInfo";}

}
