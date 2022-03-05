package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ysh.library.auth.MyUserDetail;
import ysh.library.domain.User;
import ysh.library.service.UserService;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    /**
     * 회원가입 폼
     */
    @GetMapping("/signup")
    public String signUpForm(){
        return "user/signup";
    }

    /**
     * 회원가입 진행
     */
    @PostMapping("/signup")
    public String signUp(User user){
        user.setRole("USER");
        userService.joinUser(user);
        return "redirect:/login";
    }

    /**
     * 유저 페이지
     */
    @GetMapping("/")
    public String userAccess(Model model, Authentication auth){
        MyUserDetail userDetail = (MyUserDetail) auth.getPrincipal();
        model.addAttribute("info", userDetail.getUsername());
        return "user/user_access";
    }

    /**
     *
     * basic 화면
     */
    @GetMapping("/hello")
    public String userAccess(Model model){
        return "hello";
    }
}
