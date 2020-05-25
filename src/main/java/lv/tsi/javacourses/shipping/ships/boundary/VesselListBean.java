package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.ships.control.BookingDAO;
import lv.tsi.javacourses.shipping.ships.control.VesselDAO;
import lv.tsi.javacourses.shipping.ships.model.BookingEntity;
import lv.tsi.javacourses.shipping.ships.model.PagingInfo;
import lv.tsi.javacourses.shipping.ships.model.VesselEntity;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@ViewScoped
public class VesselListBean implements Serializable {
    private List<VesselEntity> vessels;
    private List<BookingEntity> bookings;
    @Inject
    private VesselDAO vesselDAO;
    @Inject
    private BookingDAO bookingDAO;

    private PagingInfo pagingInfo = new PagingInfo();
    private static final int PAGE_SIZE = 14;


    public void init() {
        bookings = bookingDAO.findTakenBookings();
        var from = pagingInfo.from(PAGE_SIZE);
        vessels = vesselDAO.selectVesselsPage(from, PAGE_SIZE);
        var pageCount = (int) Math.ceil((double) vesselDAO.selectVesselsCount() / PAGE_SIZE);
        pagingInfo.setPageCount(pageCount);
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

    public PagingInfo getPagingInfo() {
        return pagingInfo;
    }

    public List<VesselEntity> getVessels() {
        return vessels;
    }


}
