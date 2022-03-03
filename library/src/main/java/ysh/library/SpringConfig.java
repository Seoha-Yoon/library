package ysh.library;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ysh.library.repository.MemberRepository;
import ysh.library.service.MemberService;


@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // Spring Bean에 등록
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }

}
