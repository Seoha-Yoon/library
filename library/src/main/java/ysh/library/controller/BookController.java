package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ysh.library.domain.Book;
import ysh.library.domain.Comment;
import ysh.library.service.BookService;
import ysh.library.service.CommentService;
import ysh.library.service.MemberService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;
    private final CommentService commentService;
    private final MemberService memberService;

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
        List<Comment> comments = book.getComments();

        if(comments != null && !comments.isEmpty()){
            model.addAttribute("comments", comments);
        }
        model.addAttribute("book", book);
        return "library/bookDetail";
    }

    @GetMapping("/books/{bookId}/comment")
    public String createComment(@PathVariable("bookId") Long bookId, Model model){
        model.addAttribute("bookId", bookId);
        model.addAttribute("form", new CommentForm());
        return "library/commentForm";
    }

    @PostMapping("/books/{bookId}/comment")
    public String createComment(@PathVariable("bookId") Long bookId, CommentForm form, Model model){
        Long memberId = memberService.findUserByEmail(form.getEmail());
        commentService.createComment(memberId, bookId, form.getComment());

        return "redirect:/library/books/{bookId}/detail";
    }

    @GetMapping("/books/{bookId}/comment/{commentId}/edit")
    public String updateCommentForm(@PathVariable("commentId") Long commentId,
                                Model model){
        Comment comment = commentService.findOne(commentId);
        CommentForm form = new CommentForm();
        form.setEmail(comment.getMember().getEmail());
        form.setComment(comment.getComments());
        model.addAttribute("form", form);

        return "library/editCommentForm";
    }

    @PostMapping("/books/{bookId}/comment/{commentId}/edit")
    public String updateComment(@PathVariable("commentId") Long commentId,
                                CommentForm form, Model model){
        commentService.editComment(commentId, form.getComment());
        return "redirect:/library/books/{bookId}/detail";
    }

    @GetMapping("/books/{bookId}/comment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/library/books/{bookId}/detail";
    }
}
