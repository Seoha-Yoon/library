package ysh.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ysh.library.domain.Book;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class BookRepository {

    private final EntityManager em;

    public List<Book> findAll(){
        return em.createQuery("select b from Book b", Book.class)
                .getResultList();
    }
}
