package ysh.library.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Book;
import ysh.library.domain.Comment;
import ysh.library.domain.Member;
import ysh.library.repository.BookRepository;
import ysh.library.repository.CommentRepository;
import ysh.library.repository.MemberRepository;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CommentService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    @Transactional
    public Long createComment(Long memberId, Long bookId, String comment){
        Member member = memberRepository.findOne(memberId);
        Book book = bookRepository.findOne(bookId);


        Comment comments = Comment.createComment(member, book, comment);
        commentRepository.save(comments);
        return comments.getId();
    }

    public List<Comment> findComments(){
        return commentRepository.findAll();
    }

    public Comment findOne(Long commentId){
        return commentRepository.findOne(commentId);
    }

    @Transactional
    public void editComment(Long id, String comment){
        Comment c = commentRepository.findOne(id);
        c.setComments(comment);
        c.setModifiedDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm")));
    }

    @Transactional
    public void deleteComment(Long id){
        commentRepository.delete(id);
    }
}
