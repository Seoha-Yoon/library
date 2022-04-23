package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ysh.library.auth.UserDetailsImpl;
import ysh.library.domain.Member;
import ysh.library.domain.Rent;
import ysh.library.service.MemberService;
import ysh.library.service.RentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {

    private final RentService rentService;
    private final MemberService memberService;

    @GetMapping
    public String myPageHome(Model model, @AuthenticationPrincipal UserDetailsImpl currentMember){

        MemberForm form = new MemberForm();
        Long memberId = memberService.findUserByEmail(currentMember.getUsername());
        Member member = memberService.findOne(memberId);
        form.setNickname(member.getNickname());
        model.addAttribute("form", form);
        return "mypage/main";
    }

    @GetMapping("rented")
    public String rentBookList(Model model, @AuthenticationPrincipal UserDetailsImpl currentMember){
        List<Rent> rents = rentService.findRent(currentMember.getUsername());

        model.addAttribute("rents", rents);

        return "mypage/rentBookList";
    }

    @DeleteMapping("rented/{rentId}")
    public String returnBook(@PathVariable("rentId") Long rentId){
        rentService.returnBook(rentId);
        return "redirect:/mypage/rented/";
    }

    @GetMapping("editInfo")
    public String editInfo(@AuthenticationPrincipal UserDetailsImpl currentMember, Model model){
        Long memberId = memberService.findUserByEmail(currentMember.getUsername());
        Member member = memberService.findOne(memberId);

        MemberForm form = new MemberForm();
        form.setName(member.getName());
        form.setEmail(member.getEmail());
        form.setRole(member.getRole());
        form.setNickname(member.getNickname());

        model.addAttribute("form", form);

        return "mypage/editInfo";
    }

    @PostMapping("editInfo")
    public String updateInfo(@ModelAttribute("form") MemberForm form, @AuthenticationPrincipal UserDetailsImpl currentMember){
        Long memberId = memberService.findUserByEmail(currentMember.getUsername());
        memberService.updateMember(memberId, form.getNickname());
        return "redirect:/mypage";
    }

}
