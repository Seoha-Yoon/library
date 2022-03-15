package ysh.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.auth.MemberSignupRequestDto;
import ysh.library.auth.UserDetailsImpl;
import ysh.library.domain.Member;
import ysh.library.repository.MemberRepository;

import java.util.List;
import org.springframework.security.core.Authentication;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Transactional
    public Long joinUser(MemberSignupRequestDto user){
        Member member = new Member(user);
        validateDuplicateMember(member);
        member.encryptPassword(passwordEncoder);
        memberRepository.saveUser(member);
        return member.getUserId();
    }


    public Optional<Member> findUser(String email, String password){
        return memberRepository.findUser(email, password);
    }

    private void validateDuplicateMember(Member member){
        Optional<Member> userByEmail = memberRepository.findUserByEmail(member.getEmail());
        if(userByEmail.isPresent())
            throw new IllegalStateException("이미 존재하는 회원입니다.");
    }

    public Optional<Member> findUserByEmail(String email){
        return memberRepository.findUserByEmail(email);
    }

    public Member findOne(Long memberId){
        return memberRepository.findOne(memberId);
    }

}
