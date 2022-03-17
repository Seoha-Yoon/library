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
    @Column(name = "rent_id")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "rent", cascade = CascadeType.ALL)
    private List<RentBook> rentBooks = new ArrayList<>();

    private LocalDateTime rentDate; // 빌린 시간

    //==연관관계 메서드==//
    public void addRentBook(RentBook rentBook){
        rentBooks.add(rentBook);
        rentBook.setRent(this);
    }

    public void setMember(Member member){
        this.member = member;
    }

    public static Rent createRent(Member member, RentBook... rentBooks){
        Rent rent = new Rent();
        rent.setMember(member);
        for (RentBook rentBook : rentBooks) {
            rent.addRentBook(rentBook);
        }
        rent.setRentDate(LocalDateTime.now());
        return rent;
    }

    public void returnBook(){
        for (RentBook rentBook : rentBooks) {
            rentBook.returnBook();
        }
    }


}
