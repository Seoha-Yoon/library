package ysh.library.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ysh.library.domain.Member;
import ysh.library.repository.MemberRepository;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findUserByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("등록되지 않은 사용자 입니다."));
        return new UserDetailsImpl(member);
    }
}
