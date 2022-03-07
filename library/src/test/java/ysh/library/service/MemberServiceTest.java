package ysh.library.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Member;
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
        Member member = new Member("dd@dd","1234", "kim");

        // when
        Long findId = memberService.joinUser(member);

        // then
        em.flush();
        assertEquals(member, memberService.findOne(findId));
    }

    @Test
    public void 중복_회원_예외() throws Exception{
        // given
        Member member1 = new Member("dd@dd","1234", "kim");
        Member member2 = new Member("dd@dd","1234", "kim");

        // when
        memberService.joinUser(member1);

        // then
        assertThrows(IllegalStateException.class, ()->memberService.joinUser(member2));
    }



}