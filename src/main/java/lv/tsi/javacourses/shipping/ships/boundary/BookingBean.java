package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.MessagesHelper;
import lv.tsi.javacourses.shipping.ships.control.BookingDAO;
import lv.tsi.javacourses.shipping.ships.model.BookingEntity;
import lv.tsi.javacourses.shipping.ships.model.BookingStatus;
import lv.tsi.javacourses.shipping.ships.model.VesselEntity;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
public class BookingBean implements Serializable {
    @Inject
    private BookingDAO bookingDAO;
    private final List<BookingEntity> readyToGive = new ArrayList<>();
    private final List<BookingEntity> readyToTake = new ArrayList<>();

    @PostConstruct
    public void init() {
        readyToGive.clear();
        readyToTake.clear();

        var allBookings = bookingDAO.findAllActiveBookings();
        Set<VesselEntity> processedVessel = new HashSet<>();

        for (var reservation : allBookings) {
            var vessel = reservation.getVessel();
            if (processedVessel.contains(vessel)) {
                continue;
            }
            processedVessel.add(vessel);
            if (reservation.getStatus() == BookingStatus.ACTIVE) {
                readyToGive.add(reservation);
            } else {
                readyToTake.add(reservation);
            }
        }
    }


    public List<BookingEntity> getReadyToGive() {
        return readyToGive;
    }

    public List<BookingEntity> getReadyToTake() {
        return readyToTake;
    }

    public void give(BookingEntity reservation) {
        reservation.setStatus(BookingStatus.TAKEN);
        bookingDAO.save(reservation);
        bookingDAO.deleteActiveByVessel(reservation.getVessel());
        MessagesHelper.addInfoMessage(null, "Success!");
    }

    public void take(BookingEntity reservation) {
        reservation.setStatus(BookingStatus.CLOSED);
        bookingDAO.save(reservation);
        MessagesHelper.addInfoMessage(null, "Success!");
    }
}

