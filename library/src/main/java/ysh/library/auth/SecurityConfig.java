package ysh.library.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // 정적인 파일에 대한 요청들
    private static final String[] AUTH_WHITELIST = {
            "/static/**",
            "/css/**", "/js/**", "/images/**",
            // other public endpoints of your API may be appended to this array
            "/h2/**"
    };

    @Bean
    public BCryptPasswordEncoder encodePassword() {  // 회원가입 시 비밀번호 암호화에 사용할 Encoder 빈 등록
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Override
    public void configure(WebSecurity web) throws Exception {
        // 정적인 파일 요청에 대해 무시
        web.ignoring().antMatchers(AUTH_WHITELIST);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/auth/**").permitAll() // login 없이 접근 허용
                .antMatchers("/admin").hasRole("ADMIN") // admin만 접근 허용
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .defaultSuccessUrl("/auth/signIn")
                .permitAll()
                .and()
                .logout().logoutUrl("/user/logout")
                .permitAll();
        http.cors().and();
        http.csrf().disable();

    }
}
