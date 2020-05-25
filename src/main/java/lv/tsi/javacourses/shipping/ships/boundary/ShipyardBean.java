package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.MessagesHelper;
import lv.tsi.javacourses.shipping.ships.control.ShipyardDAO;
import lv.tsi.javacourses.shipping.ships.model.ShipyardEntity;
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
public class ShipyardBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ShipyardEntity.class);
    @Inject
    private ShipyardDAO shipyardDAO;

    private ShipyardEntity shipyard;
    private long id;

    public void find() {
        if (id == 0) {
            shipyard = new ShipyardEntity();
        } else {
            shipyard = shipyardDAO.getShipyardById(id);
        }
    }

    public String delete() {
        if (shipyard.getId() == null) {
            return null;
        }

        try {
            shipyardDAO.delete(shipyard);
        } catch (Exception e) {
            log.error("Cannot delete shipyard " + shipyard.getId(), e);
            MessagesHelper.addErrorMessage(null, "Cannot delete");
            return null;
        }
        return "success";
    }

    public void save() {
        try {
            if (shipyard.getId() == null) {
                shipyardDAO.create(shipyard);
                MessagesHelper.addInfoMessage(null, "Successfully Created ");
            } else {
                shipyard = shipyardDAO.update(shipyard);
                MessagesHelper.addInfoMessage(null, "Successfully Updated ");
            }
        } catch (Exception e) {
            log.error("Cannot save shipyard " + shipyard.getId(), e);
            MessagesHelper.addErrorMessage(null, "Cannot save");
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public ShipyardEntity getShipyard() {
        return shipyard;
    }

    public long getId() {
        return id;
    }
}
