package lv.tsi.javacourses.shipping.ships.control;

import lv.tsi.javacourses.shipping.ships.model.VesselTypeEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VesselTypeDAO {
    @PersistenceContext
    private EntityManager em;

    public List<VesselTypeEntity> getAllTypes() {
        return em.createQuery("select b from Vesseltype b", VesselTypeEntity.class)
                .getResultList();
    }

    public List<VesselTypeEntity> selectTypePage(int from, int size) {
        return em.createQuery("select b from Vesseltype b", VesselTypeEntity.class)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
    public long selectTypeCount() {
        return em.createQuery("select count(a) from Vesseltype a", Long.class)
                .getSingleResult();
    }

    public VesselTypeEntity getTypeById(long id) {
        return em.find(VesselTypeEntity.class, id);
    }

    public void create(VesselTypeEntity type) {
        em.persist(type);
    }

    public VesselTypeEntity update(VesselTypeEntity type) {
        var tmp = em.merge(type);
        return tmp;
    }

    public void delete (VesselTypeEntity type){
        var tmp = em.merge(type);
        em.remove(tmp);
    }
}
