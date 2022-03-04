package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties;
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
    @GetMapping("/signUp")
    public String signUpForm(){
        return "signup";
    }

    /**
     * 회원가입 진행
     */
    @PostMapping("/signUp")
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
        return "user_access";
    }
}
