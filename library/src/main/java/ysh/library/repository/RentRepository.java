package ysh.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ysh.library.domain.Book;
import ysh.library.domain.Member;
import ysh.library.domain.Rent;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class RentRepository {

    private final EntityManager em;

    public void save(Rent rent){
        em.persist(rent);
    }

    public Rent findOne(Long id){return em.find(Rent.class, id);}

    public List<Rent> findRentByEmail(String email){
        List<Rent> list = em.createQuery("select r from Rent as r where r.member.email= :email", Rent.class)
                .setParameter("email", email)
                .getResultList();
        return list;
    }

    public void deleteRent(Long id){
        Rent rent = findOne(id);
        em.remove(rent);
    }

}
