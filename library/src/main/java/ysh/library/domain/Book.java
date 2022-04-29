package ysh.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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
    private int stock;

    // 책 상태
    @Enumerated(EnumType.STRING)
    private BookStatus status = BookStatus.AVAILABLE;

    // 댓글 연관관계 매핑
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Comment> comments;

    public void changeStatus(BookStatus bookStatus) {
        status = bookStatus;
    }

    public void decreaseStock(){
        stock--;
    }

    public void increaseStock(){
        stock++;
    }
}
