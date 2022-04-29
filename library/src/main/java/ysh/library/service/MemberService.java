package ysh.library.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.dto.MemberDetailsDto;
import ysh.library.domain.dto.MemberSignupRequestDto;
import ysh.library.domain.Member;
import ysh.library.repository.MemberRepository;

import java.util.List;
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

    public Long findUserByEmail(String email){
        Optional<Member> m = memberRepository.findUserByEmail(email);
        return m.get().getUserId();
    }

    public MemberDetailsDto findOne(Long memberId){
        Member member = memberRepository.findOne(memberId);
        MemberDetailsDto memberDetailsDto = new MemberDetailsDto(member.getEmail(), member.getName(), member.getRole(), member.getNickname());
        return memberDetailsDto;
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    /**
     * 영속성 컨텍스트가 멤버 수정
     */
    public void updateMember(Long id, String nickname){
        Member member = memberRepository.findOne(id);
        member.setNickname(nickname);
    }
}
