package lv.tsi.javacourses.shipping.ships.control;

import lv.tsi.javacourses.shipping.auth.model.UserEntity;
import lv.tsi.javacourses.shipping.ships.model.BookingEntity;
import lv.tsi.javacourses.shipping.ships.model.BookingStatus;
import lv.tsi.javacourses.shipping.ships.model.VesselEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookingDAO {
    @PersistenceContext
    private EntityManager em;

    public BookingEntity createReservation(VesselEntity vessel, UserEntity user) {
        var result = new BookingEntity();
        result.setVessel(vessel);
        result.setUser(user);
        result.setStatus(BookingStatus.ACTIVE);
        em.persist(result);
        return result;
    }
    public List<BookingEntity> findTakenBookings() {
        return em.createQuery("select b from Booking b " +
                "where  b.status = 'TAKEN'" , BookingEntity.class)
                .getResultList();
    }

    public List<BookingEntity> findActiveBookings(VesselEntity vessel, UserEntity user) {
        return em.createQuery("select b from Booking b " +
                "where b.vessel = :vessel and b.user = :user and b.status <> 'CLOSED'" +
                "order by b.create", BookingEntity.class)
                .setParameter("user", user)
                .setParameter("vessel", vessel)
                .getResultList();
    }

    public List<BookingEntity> findAllActiveBookings() {
        return em.createQuery("select b from Booking b " +
                "where b.status <> 'CLOSED'" +
                "order by b.create", BookingEntity.class)
                .getResultList();
    }

    public List<BookingEntity> findAllUsersBookings(UserEntity user) {
        return em.createQuery("select b from Booking b " +
                "where b.user = :user and b.status <> 'CLOSED'" +
                "order by b.create", BookingEntity.class)
                .setParameter("user", user)
                .getResultList();
    }

    public void deleteActiveByVessel(VesselEntity vessel) {
        em.createQuery("delete from Booking b where b.status ='ACTIVE'" +
                " and b.vessel.id =:vessel")
                .setParameter("vessel", vessel.getId())
                .executeUpdate();
    }


    public void save(BookingEntity booking) {
        em.merge(booking);
    }

}
