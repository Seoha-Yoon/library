package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ysh.library.domain.Book;
import ysh.library.service.BookService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;

    // 로그인한 유저만 접근 가능한 라이브러리 홈페이지
    @GetMapping
    public String dashboard(Model model)
    {
        return "library/main";
    }

    @GetMapping("/books")
    public String list(Model model) {
        List<Book> books = bookService.findBooks();
        model.addAttribute("books",books);
        return "library/bookList";
    }

    @GetMapping("/books/{bookId}/detail")
    public String bookDetail(@PathVariable("bookId") Long bookId, Model model){
        Book book = bookService.findOne(bookId);
        model.addAttribute("book", book);
        return "library/bookDetail";
    }
}
