package lv.tsi.javacourses.shipping.ships.control;

import lv.tsi.javacourses.shipping.ships.model.FlagEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class FlagDAO {
    @PersistenceContext
    private EntityManager em;

    public List<FlagEntity> getAllFlags() {
        return em.createQuery("select b from Flag b", FlagEntity.class)
                .getResultList();
    }
    public List<FlagEntity> selectFlagPage(int from, int size) {
        return em.createQuery("select b from Flag b", FlagEntity.class)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
    public long selectFlagCount() {
        return em.createQuery("select count(a) from Flag a", Long.class)
                .getSingleResult();
    }

    public FlagEntity getFlagById(long id) {
        return em.find(FlagEntity.class, id);
    }

    public void create(FlagEntity flag) {
        em.persist(flag);
    }

    public FlagEntity update(FlagEntity flag) {
        var tmp = em.merge(flag);
        return tmp;
    }

    public void delete (FlagEntity flag){
        var tmp = em.merge(flag);
        em.remove(tmp);
    }
}
