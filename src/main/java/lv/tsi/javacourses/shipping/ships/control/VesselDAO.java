package lv.tsi.javacourses.shipping.ships.control;

import lv.tsi.javacourses.shipping.ships.model.VesselEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class VesselDAO {
    @PersistenceContext
    private EntityManager em;

    public VesselEntity getVesselById(long vesselId) {
        return em.find(VesselEntity.class, vesselId);
    }

//    public List<VesselEntity> searchVessels(int from, int size, String str) {
//        return em.createQuery("select b from Vessel b WHERE b.name LIKE :str"+
//                " or b.vesseltype.type LIKE :str", VesselEntity.class)
//                .setParameter("str", "%" + str + "%")
//                .setFirstResult(from)
//                .setMaxResults(size)
//                .getResultList();
//    }

    public List<VesselEntity> searchVessels(String str) {
        return em.createQuery("select b from Vessel b WHERE b.name LIKE :str"+
                " or b.vesseltype.type LIKE :str", VesselEntity.class)
                .setParameter("str", "%" + str + "%")
                .getResultList();
    }


    public VesselEntity findAndLockVessel(long vesselId) {
        return em.find(VesselEntity.class, vesselId, LockModeType.PESSIMISTIC_WRITE);
    }

    public long selectVesselsCount() {
        return em.createQuery("select count(a) from Vessel a", Long.class)
                .getSingleResult();
    }

    public List<VesselEntity> selectVesselsPage(int from, int size) {
        return em.createQuery("select b from Vessel b", VesselEntity.class)
                .setFirstResult(from)
                .setMaxResults(size)
                .getResultList();
    }
//    public List<VesselEntity> getVesselsByShipyard(ShipyardEntity shipyard) {
//        return em.createQuery("select b from Vessel b where b.shipyard = :shipyard", VesselEntity.class)
//                .setParameter("shipyard", shipyard)
//                .getResultList();
//    }

//    public List<VesselEntity> getVesselsByShipyardId(long shipyardId) {
//        return em.createQuery("select b from Vessel b where b.shipyard.id = :shipyardId", VesselEntity.class)
//                .setParameter("shipyardId", shipyardId)
//                .getResultList();
//    }

    public void create(VesselEntity vessel) {
        em.persist(vessel);
    }

    public void update(VesselEntity vessel) {
        em.merge(vessel);
    }
}
