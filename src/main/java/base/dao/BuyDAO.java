package base.dao;

import base.model.Buy;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BuyDAO implements GenericDAO <Buy, Integer> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void create(Buy object) {
        entityManager.persist(object);
    }

    @Override
    public void update(Buy object) {
        entityManager.refresh(object);
    }

    @Override
    public void delete(Integer key) {
        Buy buy = getObjectById(key);
        if(buy != null) entityManager.remove(buy);
    }

    @Override
    public Buy getObjectById(Integer key) {
        return entityManager.find(Buy.class, key);
    }

    @Override
    public List<Buy> getAllObjects() {

        List<Buy> objects = entityManager.createQuery("from Buy").getResultList();
        return objects;
    }
}
