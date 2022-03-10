package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {

    // 로그인한 유저만 접근 가능
    @GetMapping("dashboard")
    public String dashboard() {
        return "dashboard";
    }

    // admin만 접근 가능
    @GetMapping("admin")
    public String admin() {
        return "admin";
    }
}
