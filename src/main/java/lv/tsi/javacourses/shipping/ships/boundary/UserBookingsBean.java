package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.auth.boundary.CurrentUser;
import lv.tsi.javacourses.shipping.ships.control.BookingDAO;
import lv.tsi.javacourses.shipping.ships.model.BookingEntity;
import lv.tsi.javacourses.shipping.ships.model.BookingStatus;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
public class UserBookingsBean implements Serializable {
    @Inject
    private BookingDAO bookingDAO;
    @Inject
    private CurrentUser currentUser;

    private final List<BookingEntity> activeBookings = new ArrayList<>();
    private final List<BookingEntity> takenBookings = new ArrayList<>();

    public void init() {
        activeBookings.clear();
        takenBookings.clear();

        var allBookings = bookingDAO.findAllUsersBookings(currentUser.getUser());

        for (var reservation : allBookings) {
            if (reservation.getStatus() == BookingStatus.ACTIVE) {
                activeBookings.add(reservation);
            } else if(reservation.getStatus() == BookingStatus.TAKEN){
                takenBookings.add(reservation);
            }
        }
    }

    public String convert (Instant instant){
       return LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).format( DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
    }

    public List<BookingEntity> getActiveBookings() {
        return activeBookings;
    }

    public List<BookingEntity> getTakenBookings() {
        return takenBookings;
    }
}
