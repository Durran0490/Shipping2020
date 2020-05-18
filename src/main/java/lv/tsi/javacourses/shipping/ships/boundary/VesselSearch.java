package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.ships.control.BookingDAO;
import lv.tsi.javacourses.shipping.ships.control.VesselDAO;
import lv.tsi.javacourses.shipping.ships.model.BookingEntity;
import lv.tsi.javacourses.shipping.ships.model.VesselEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class VesselSearch implements Serializable {
    private List<VesselEntity> vessels;
    private List<BookingEntity> bookings;
    @Inject
    private VesselDAO vesselDAO;
    @Inject
    private BookingDAO bookingDAO;

    private String str;


    public String search(String search) {
        this.str = search;
        return "/app/searchVessel.xhtml?faces-redirect=true&search=" + str;
    }

    public void searchVessel(String search) {
        bookings = bookingDAO.findTakenBookings();
        vessels = vesselDAO.searchVessels(search);
    }

    public boolean vesselStatus(long id) {
        if (bookings != null) {
            for (BookingEntity b : bookings) {
                if (b.getVessel().getId() == id) {
                    return true;
                }
            }
        }
        return false;
    }

    public List<VesselEntity> getVessels() {
        return vessels;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }
}