package ysh.library.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "rent_book")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RentBook {
    @Id
    @GeneratedValue
    @Column(name = "rent_book_id")
    private long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rent_id")
    private Rent rent;


    //==생성 매서드==/
    public static RentBook createRentBook(Book book){
        RentBook rentBook = new RentBook();
        rentBook.setBook(book);
        book.decreaseStock();

        if(book.getStock() <= 0)
            book.changeStatus(BookStatus.UNAVAILABLE);
        return rentBook;
    }

    //==비즈니스 로직==/
    public void returnBook(){
        Book book = getBook();
        book.increaseStock();
        if(book.getStock()>0)
            getBook().setStatus(BookStatus.AVAILABLE);
    }

}
