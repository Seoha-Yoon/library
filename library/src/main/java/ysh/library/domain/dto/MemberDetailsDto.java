package ysh.library.domain.dto;

import lombok.Getter;
import lombok.Setter;
import ysh.library.auth.Role;

@Getter @Setter
public class MemberDetailsDto {
    private String email;
    private String name;
    private Role role;
    private String nickname;

    public MemberDetailsDto(String email, String name, Role role, String nickname) {
        this.email = email;
        this.name = name;
        this.role =role;
        this.nickname = nickname;
    }
}
