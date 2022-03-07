package ysh.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Member;
import ysh.library.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long joinUser(Member user){
        validateDuplicateMember(user);
        memberRepository.saveUser(user);
        return user.getUserId();
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
