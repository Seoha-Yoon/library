package ysh.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Rent {

    @Id @GeneratedValue
    @Column(name = "rental_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "rent_id", cascade = CascadeType.ALL)
    private List<RentBook> rentBooks = new ArrayList<>();

    private LocalDateTime rentDate; // 빌린 시간


}
