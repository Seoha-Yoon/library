package ysh.library.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ysh.library.auth.UserDetailsImpl;
import ysh.library.domain.Book;
import ysh.library.domain.Comment;
import ysh.library.service.BookService;
import ysh.library.service.CommentService;
import ysh.library.service.MemberService;
import ysh.library.service.RentService;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/library")
public class BookController {

    private final BookService bookService;
    private final CommentService commentService;
    private final MemberService memberService;
    private final RentService rentService;

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

    @PostMapping("/books/{bookId}")
    public String rentBook(@AuthenticationPrincipal UserDetailsImpl currentMember,
                             @PathVariable("bookId") Long bookId){
        Long memberId = memberService.findUserByEmail(currentMember.getUsername());
        rentService.rent(memberId, bookId);
        return "redirect:/library/books";
    }

    @GetMapping("/books/{bookId}")
    public String bookDetail(@PathVariable("bookId") Long bookId, Model model, @AuthenticationPrincipal UserDetailsImpl curMember){
        Book book = bookService.findOne(bookId);
        List<Comment> comments = book.getComments();
        Long memberId = memberService.findUserByEmail(curMember.getUsername());

        if(comments != null && !comments.isEmpty()){
            model.addAttribute("comments", comments);
        }
        model.addAttribute("book", book);
        model.addAttribute("memberId", memberId);
        return "library/bookDetail";
    }

    @GetMapping("/books/{bookId}/comment")
    public String createComment(@PathVariable("bookId") Long bookId, Model model){
        model.addAttribute("bookId", bookId);
        model.addAttribute("form", new CommentForm());
        return "library/commentForm";
    }

    @PostMapping("/books/{bookId}/comment")
    public String createComment(@PathVariable("bookId") Long bookId, CommentForm form, @AuthenticationPrincipal UserDetailsImpl curMember){
        Long memberId = memberService.findUserByEmail(curMember.getUsername());
        commentService.createComment(memberId, bookId, form.getComment());

        return "redirect:/library/books/{bookId}";
    }

    @GetMapping("/books/{bookId}/comment/{commentId}/edit")
    public String updateCommentForm(@PathVariable("commentId") Long commentId,
                                Model model){
        Comment comment = commentService.findOne(commentId);
        CommentForm form = new CommentForm();
        form.setComment(comment.getComments());
        model.addAttribute("form", form);

        return "library/editCommentForm";
    }

    @PostMapping("/books/{bookId}/comment/{commentId}/edit")
    public String updateComment(@PathVariable("commentId") Long commentId,
                                CommentForm form){
        commentService.editComment(commentId, form.getComment());
        return "redirect:/library/books/{bookId}/detail";
    }

    @GetMapping("/books/{bookId}/comment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
        return "redirect:/library/books/{bookId}/detail";
    }
}
