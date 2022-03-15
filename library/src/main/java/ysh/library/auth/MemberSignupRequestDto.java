package ysh.library.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSignupRequestDto {
    private String email;
    private String password;
    private String name;
    private Role role;

    public MemberSignupRequestDto(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role =role;
    }
}
