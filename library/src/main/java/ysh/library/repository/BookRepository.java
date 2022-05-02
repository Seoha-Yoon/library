package ysh.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ysh.library.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;


@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public Book findOne(Long id){ return em.find(Book.class, id);}

    public Optional<Book> findByBookName(String title){
        List<Book> book = em.createQuery("select b from Book b where b.title= :title", Book.class)
                .setParameter("title", title)
                .getResultList();
        return book.stream().findAny();
    }

    public Optional<Book> findByISBN(String isbn){
        List<Book> book = em.createQuery("select b from Book b where b.isbn= :isbn", Book.class)
                .setParameter("isbn", isbn)
                .getResultList();
        return book.stream().findAny();
    }

    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
}
