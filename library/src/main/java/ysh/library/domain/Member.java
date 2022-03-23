package ysh.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import ysh.library.auth.MemberSignupRequestDto;
import ysh.library.auth.Role;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
    @Id
    @Column(name = "member_id")
    @GeneratedValue
    private Long userId;
    private String email;
    private String password;
    private String name;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "RegDate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToMany(mappedBy = "member")
    private List<Comment> comments = new ArrayList<>();

    public Member(MemberSignupRequestDto request) {
        this.email = request.getEmail();
        this.password = request.getPassword();
        this.name = request.getName();
        date = LocalDate.now();
        role = request.getRole();
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

}
