package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ysh.library.domain.dto.MemberSignupRequestDto;
import ysh.library.service.MemberService;
import javax.validation.Valid;


@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;

    /**
     * 로그인 성공시 화면
     */
    @GetMapping("/signIn")
    public String signIn() {
        return "auth/login_success";
    }

    /**
     * 회원가입
     */
    @GetMapping("/signUp")
    public String createSignUpForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "auth/sign_up";
    }

    @PostMapping("/signUp")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors())
            return "auth/sign_up";
        MemberSignupRequestDto request = MemberSignupRequestDto.builder()
                .email(form.getEmail())
                .name(form.getName())
                .nickname(form.getNickname())
                .password(form.getPassword())
                .role(form.getRole())
                .build();

        memberService.joinUser(request);
        return "redirect:/auth/login";
    }

    /**
     * spring security와 연결된 로그인 페이지
     */
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }
}
