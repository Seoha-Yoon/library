package ysh.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.auth.Role;
import ysh.library.domain.Member;
import ysh.library.domain.dto.MemberSignupRequestDto;
import ysh.library.repository.MemberRepository;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class MemberServiceTest {
    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;
    @Autowired EntityManager em;

    @Test
    public void 회원가입() throws Exception{

        // given
        MemberSignupRequestDto member =
                MemberSignupRequestDto.builder()
                        .name("kim")
                        .email("ab@cd")
                        .password("1234")
                        .nickname("hi")
                        .role(Role.USER)
                        .build();

        // when
        Long findId = memberService.joinUser(member);

        // then
        em.flush();
        assertEquals(member.getName(), memberService.findOne(findId).getName());
    }


}