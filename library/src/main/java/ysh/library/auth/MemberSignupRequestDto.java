package ysh.library.auth;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberSignupRequestDto {
    private String email;
    private String password;
    private String name;

    public MemberSignupRequestDto(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
}
