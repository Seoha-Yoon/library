package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ysh.library.auth.UserDetailsImpl;
import ysh.library.domain.Book;
import ysh.library.domain.Member;
import ysh.library.domain.Rent;
import ysh.library.domain.RentBook;
import ysh.library.repository.MemberRepository;
import ysh.library.repository.RentRepository;
import ysh.library.service.RentService;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {

    private final RentService rentService;

    @GetMapping
    public String myPageHome(Model model, @AuthenticationPrincipal UserDetailsImpl currentMember){

        MemberForm form = new MemberForm();
        form.setEmail(currentMember.getUsername());
        model.addAttribute("form", form);
        return "mypage/main";
    }

    @GetMapping("rented")
    public String rentBookList(Model model, @AuthenticationPrincipal UserDetailsImpl currentMember){
        List<Rent> rents = rentService.findRent(currentMember.getUsername());

        model.addAttribute("rents", rents);

        return "mypage/rentBookList";
    }

    @GetMapping("rented/{rentId}/return")
    public String returnBook(@PathVariable("rentId") Long rentId, Model model){
        rentService.returnBook(rentId);
        return "redirect:mypage/rented";
    }

}
