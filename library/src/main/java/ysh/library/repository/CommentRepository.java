package ysh.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ysh.library.domain.Comment;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {

    private final EntityManager em;

    public void save(Comment comment){
        if(comment.getId() == null){
            em.persist(comment);
        }else{
            em.merge(comment);
        }
    }

    public Comment findOne(Long id){
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll(){
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public void delete(Long id){
        Comment comment = findOne(id);
        em.remove(comment);
    }

}
