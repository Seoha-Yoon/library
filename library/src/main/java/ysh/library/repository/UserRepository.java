package ysh.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ysh.library.domain.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final EntityManager em;

    public void saveUser(User user){
        em.persist(user);
    }

    public User findUserByEmail(String email){
        TypedQuery<User> query = em.createQuery("select m from User as m where m.email=?1", User.class)
                .setParameter(1, email);
        return query.getSingleResult();
    }
}
