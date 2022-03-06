package ysh.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Member;
import ysh.library.repository.MemberRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void joinUser(Member user){
        memberRepository.saveUser(user);
    }

    public Optional<Member> findUser(String email, String password){
        return memberRepository.findUser(email, password);
    }


}
