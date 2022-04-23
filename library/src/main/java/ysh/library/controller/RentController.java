package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ysh.library.auth.UserDetailsImpl;
import ysh.library.domain.Book;
import ysh.library.domain.Member;
import ysh.library.service.BookService;
import ysh.library.service.MemberService;
import ysh.library.service.RentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/library/rent")
public class RentController {

    private final RentService rentService;
    private final MemberService memberService;
    private final BookService bookService;

    @GetMapping
    public String createForm(Model model){
        List<Member> members = memberService.findMembers();
        List<Book> books = bookService.findBooks();

        model.addAttribute("members", members);
        model.addAttribute("books", books);

        return "library/rentForm";
    }

    @PostMapping
    public String createForm(@AuthenticationPrincipal UserDetailsImpl currentMember,
                             @RequestParam("bookId") Long bookId){
        Long memberId = memberService.findUserByEmail(currentMember.getUsername());
        rentService.rent(memberId, bookId);
        return "redirect:/library";
    }

}
