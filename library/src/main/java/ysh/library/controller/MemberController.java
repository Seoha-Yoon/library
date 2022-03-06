package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ysh.library.domain.Member;
import ysh.library.service.MemberService;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class MemberController {
    private final MemberService memberService;

    /**
     * 로그인 페이지
     */
    @GetMapping("/")
    public String login() {
        return "auth/login";
    }

    /**
     * 로그인 유무
     */

    @PostMapping("/signIn")
    public String signIn(String inputEmail, String inputPassword) {
        log.info("id : {} , pw : {}", inputEmail, inputPassword);
        Optional<Member> member = memberService.findUser(inputEmail, inputPassword);
        if (member.isPresent()) {
            return "auth/login_success";
        }
        return "auth/login_fail";
    }

    /**
     * 회원가입
     */
    @GetMapping("/signUp")
    public String signUp(){
        return "auth/sign_up";
    }

    /**
     * 회원가입 성공
     */
    @PostMapping("/signUp")
    public String create(Member member){
        member.setDate(LocalDate.now());
        memberService.joinUser(member);
        return "redirect:/";
    }
}
