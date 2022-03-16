package ysh.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Book;
import ysh.library.repository.BookRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public List<Book> findBooks(){
        return bookRepository.findAll();
    }
}
