package ysh.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ysh.library.domain.Member;
import ysh.library.service.MemberService;

import java.util.List;

// Spring Container가 Controller 객체 생성해서 들고있음
@Controller
public class MemberController {

    // 객체를 new로 생성하지 않는다.
    private MemberService memberService;

    // Spring Container에 있는 memberService와 연결을 함. -> DEPENDENCY INJECTION
    // 생성자 이용한 주입. 요즘 가장 많이 씀
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService = "+memberService.getClass());
    }

    // url + enter, data 조회할 때
    @GetMapping("/members/new")
    public String createForm(){
        return "members/createMemberForm";
    }

    // data 전달할 때
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
