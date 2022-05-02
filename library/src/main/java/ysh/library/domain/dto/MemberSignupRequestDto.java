package ysh.library.domain.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import ysh.library.auth.Role;

@Getter @Setter
@Builder
public class MemberSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private Role role;
    private String nickname;

    public MemberSignupRequestDto(String email, String password, String name, Role role, String nickname) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role =role;
        this.nickname = nickname;
    }
}
