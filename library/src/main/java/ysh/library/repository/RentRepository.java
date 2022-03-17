package ysh.library.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ysh.library.domain.Rent;

import javax.persistence.EntityManager;

@Repository
@AllArgsConstructor
public class RentRepository {

    private final EntityManager em;

    public void save(Rent rent){
        em.persist(rent);
    }

    public Rent findOne(Long id){return em.find(Rent.class, id);}
}
