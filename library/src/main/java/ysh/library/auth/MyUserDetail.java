package ysh.library.auth;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ysh.library.domain.User;

import java.util.Collection;
import java.util.Collections;

public class MyUserDetail implements UserDetails {

    private String email;
    private String password;
    private String auth;

    public MyUserDetail(User user) {
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.auth = "ROLE_"+user.getRole();
    }

    // 계정이 가지고 있는 권한 목록 반환
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.auth));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    // 계정 만료 여부 (false = 만료)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 계정 잠김 여부 (false = 잠김)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 계정 비밀번호 만료 여부 (false = 만료)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 계정 활성화 여부 (false = 비활성)
    @Override
    public boolean isEnabled() {
        return true;
    }
}
