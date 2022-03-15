package ysh.library.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ysh.library.domain.Member;
import ysh.library.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final MemberService memberService;

    // 로그인한 유저만 접근 가능
    @GetMapping("dashboard")
    public String dashboard(Model model)
    {

        return "library/main";
    }

    // admin만 접근 가능
    @GetMapping("admin")
    public String admin(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "member/memberList";
    }
}
