package lv.tsi.javacourses.shipping.ships.control;

import lv.tsi.javacourses.shipping.ships.model.ShipyardEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class ShipyardDAO {
    @PersistenceContext
    private EntityManager em;

    public List<ShipyardEntity> getAllShipyards() {
        return em.createQuery("select b from Shipyard b", ShipyardEntity.class)
                .getResultList();
    }

    public ShipyardEntity getShipyardById(long id) {
        return em.find(ShipyardEntity.class, id);
    }

    public List<ShipyardEntity> selectShipyardPage(int from, int size) {
        return em.createQuery("select b from Shipyard b", ShipyardEntity.class)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
    public long selectShipyardCount() {
        return em.createQuery("select count(a) from Shipyard a", Long.class)
                .getSingleResult();
    }

    public void create(ShipyardEntity shipyard) {
        em.persist(shipyard);
    }

    public ShipyardEntity update(ShipyardEntity shipyard) {
        var tmp = em.merge(shipyard);
        return tmp;
    }

    public void delete (ShipyardEntity shipyard){
        var tmp = em.merge(shipyard);
        em.remove(tmp);
    }
}
