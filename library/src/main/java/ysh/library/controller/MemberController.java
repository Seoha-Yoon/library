package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import ysh.library.auth.MemberSignupRequestDto;
import ysh.library.domain.Member;
import ysh.library.service.MemberService;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/auth")
public class MemberController {
    private final MemberService memberService;


    /**
     * 로그인 유무
     */
    //@PostMapping("/signIn")
//    public String signIn(String inputEmail, String inputPassword) {
//        log.info("id : {} , pw : {}", inputEmail, inputPassword);
//        Optional<Member> member = memberService.findUser(inputEmail, inputPassword);
//        if (member.isPresent()) {
//            return "auth/login_success";
//        }
//        return "auth/login_fail";
//    }

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

    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

    /**
     * 회원가입 성공
     */
    // @PostMapping("/signUp")
//    public String create(@Valid MemberForm form, BindingResult result){
//        if(result.hasErrors())
//            return "auth/sign_up";
//
//        Member member = new Member(form.getEmail(), form.getPassword(), form.getName());
//        member.setDate(LocalDate.now());
//        memberService.joinUser(member);
//        return "redirect:/";
//    }

    @PostMapping("/signUp")
    public String create(@Valid MemberForm form, BindingResult result){
        if(result.hasErrors())
            return "auth/sign_up";

        MemberSignupRequestDto request = new MemberSignupRequestDto(form.getEmail(), form.getPassword(), form.getName());
        memberService.joinUser(request);
        return "redirect:/login";
    }
}
