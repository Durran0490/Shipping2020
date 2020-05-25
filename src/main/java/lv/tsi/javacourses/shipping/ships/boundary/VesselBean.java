package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.MessagesHelper;
import lv.tsi.javacourses.shipping.auth.boundary.CurrentUser;
import lv.tsi.javacourses.shipping.ships.control.*;
import lv.tsi.javacourses.shipping.ships.model.*;
import org.omnifaces.util.Servlets;
import org.omnifaces.util.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class VesselBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(VesselBean.class);

    @Inject
    private VesselDAO vesselDAO;
    @Inject
    private ShipyardDAO shipyardDAO;
    @Inject
    private FlagDAO flagDAO;
    @Inject
    private BookingDAO bookingDAO;
    @Inject
    private VesselTypeDAO vesselTypeDAO;
    @Inject
    private CurrentUser currentUser;
    @Inject
    private FileDAO fileDAO;

    private long id;

    private VesselEntity vessel;
    private List<ShipyardEntity> shipyards;
    private List<FlagEntity> flags;
    private List<VesselTypeEntity> type;

    private Part coverPart;

    @PostConstruct
    public void loadInfo() {
        shipyards = shipyardDAO.getAllShipyards();
        flags = flagDAO.getAllFlags();
        type = vesselTypeDAO.getAllTypes();
    }

    public void loadVessel() {
        vessel = vesselDAO.getVesselById(id);
    }

    public void create() {
        vessel = new VesselEntity();
    }

    public void save() {
        log.trace("Save started");
        if (coverPart != null) {
            log.info("Uploaded file name:{}; content-type:{}; size:{}; submittedName:{}",
                    coverPart.getName(),
                    coverPart.getContentType(),
                    coverPart.getSize(),
                    coverPart.getSubmittedFileName());
            var cover = new FileEntity();
            cover.setContentType(coverPart.getContentType());
            cover.setFileName(coverPart.getSubmittedFileName());
            cover.setData(loadBytesFromPart(coverPart));
            fileDAO.save(cover);
            vessel.setCover(cover);
        }
        if (vessel.getId() == null) {
            vesselDAO.create(vessel);
            MessagesHelper.addInfoMessage(null, "Saved successfully!");
        } else {
            vesselDAO.update(vessel);
            MessagesHelper.addInfoMessage(null, "Updated successfully!");
        }
    }


    private byte[] loadBytesFromPart(Part part) {
        try (var is = part.getInputStream()) {
            return Utils.toByteArray(is);
        } catch (IOException e) {
            log.error("Cannot read part", e);
            MessagesHelper.addErrorMessage(part.getName(), "Sorry, file not processed!");
            return null;
        }
    }

    @Transactional
    public String reserve() {
        var user = currentUser.getUser();
        vesselDAO.findAndLockVessel(vessel.getId());
        var rl = bookingDAO.findActiveBookings(vessel, user);
        if (!rl.isEmpty()) {
            MessagesHelper.addErrorMessage(null,
                    "You have active reservation of this Vessel");
            return null;
        }
        MessagesHelper.addInfoMessage(null, "Reservation placed for approval");
        bookingDAO.createReservation(vessel, user);
        return null;
    }

    public boolean checkVesselImage() {
        if (this.vessel != null) {
            return vessel.getCover() != null;
        }
        return false;
    }

    public void reload() {
        try {
            ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
            ec.redirect(((HttpServletRequest) ec.getRequest()).getRequestURI());
        } catch (IOException e) {
            log.error("Cannot reload part", e);
        }
    }

    public VesselEntity getVessel() {
        return vessel;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<ShipyardEntity> getShipyards() {
        return shipyards;
    }

    public List<FlagEntity> getFlags() {
        return flags;
    }

    public List<VesselTypeEntity> getType() {
        return type;
    }

    public Part getCoverPart() {
        return coverPart;
    }

    public void setCoverPart(Part coverPart) {
        this.coverPart = coverPart;
    }

}
