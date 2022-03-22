package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ysh.library.auth.UserDetailsImpl;
import ysh.library.domain.Member;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {

    @GetMapping
    public String myPageHome(Model model, @AuthenticationPrincipal UserDetailsImpl currentMember){

        MemberForm form = new MemberForm();
        form.setEmail(currentMember.getUsername());
        model.addAttribute("form", form);
        return "mypage/info";
    }

}
