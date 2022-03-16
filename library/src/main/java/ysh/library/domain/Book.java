package ysh.library.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;

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

}
