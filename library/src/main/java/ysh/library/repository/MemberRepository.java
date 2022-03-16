package ysh.library.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import ysh.library.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    private final EntityManager em;

    public void saveUser(Member member){
        em.persist(member);
    }

    public Optional<Member> findUserByEmail(String email){
        List<Member> list = em.createQuery("select m from Member as m where m.email= :email", Member.class)
                .setParameter("email", email)
                .getResultList();
        return list.stream().findAny();
    }

    public Optional<Member> findUser(String email, String password){
        List<Member> list = em.createQuery("select m from Member as m where m.email= :email and m.password= :password", Member.class)
                .setParameter("email", email)
                .setParameter("password", password)
                .getResultList();
        return list.stream().findAny();
    }

    public Member findOne(Long id){
        return em.find(Member.class, id);
    }

    public List<Member> findAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
}
