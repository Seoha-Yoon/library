package ysh.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Book {

    @Id
    private Long id;

    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private int year;

    // 책 상태
    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;

    public void changeStatus(BookStatus bookStatus) {
        status = bookStatus;
    }
}
