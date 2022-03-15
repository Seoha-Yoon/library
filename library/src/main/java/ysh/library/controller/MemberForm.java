package ysh.library.controller;

import lombok.Getter;
import lombok.Setter;
import ysh.library.auth.Role;

import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {

    @NotEmpty(message = "이메일을 입력해주세요.")
    private String email;

    @NotEmpty(message = "비밀번호를 입력해주세요.")
    private String password;
    private String name;
    private Role role;
}
