package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.MessagesHelper;
import lv.tsi.javacourses.shipping.ships.control.VesselTypeDAO;
import lv.tsi.javacourses.shipping.ships.model.VesselTypeEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class VesselTypeBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(VesselTypeEntity.class);
    @Inject
    private VesselTypeDAO vesselTypeDAO;

    private VesselTypeEntity type;
    private long id;


    public void find() {
        if (id == 0) {
            type = new VesselTypeEntity();
        } else {
            type = vesselTypeDAO.getTypeById(id);
        }
    }

    public String delete() {
        if (type.getId() == null) {
            return null;
        }

        try {
            vesselTypeDAO.delete(type);
        } catch (Exception e) {
            log.error("Cannot delete author " + type.getId(), e);
            MessagesHelper.addErrorMessage(null, "Cannot delete");
            return null;
        }

        return "success";
    }

    public void save() {
        try {
            if (type.getId() == null) {
                vesselTypeDAO.create(type);
                MessagesHelper.addInfoMessage(null, "Successfully Created ");
            } else {
                type = vesselTypeDAO.update(type);
                MessagesHelper.addInfoMessage(null, "Successfully Updated ");
            }
        } catch (Exception e) {
            log.error("Cannot save shipyard " + type.getId(), e);
            MessagesHelper.addErrorMessage(null, "Cannot delete");
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public VesselTypeEntity getType() {
        return type;
    }

    public long getId() {
        return id;
    }
}