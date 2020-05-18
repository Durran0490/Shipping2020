package lv.tsi.javacourses.shipping.ships.boundary;

import lv.tsi.javacourses.shipping.MessagesHelper;
import lv.tsi.javacourses.shipping.ships.control.FlagDAO;
import lv.tsi.javacourses.shipping.ships.control.ShipyardDAO;
import lv.tsi.javacourses.shipping.ships.model.FlagEntity;
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
public class FlagBean implements Serializable {
    private static final Logger log = LoggerFactory.getLogger(ShipyardEntity.class);
    @Inject
    private FlagDAO flagDAO;

    private FlagEntity flag;
    private long id;

    public void find() {
        if (id == 0) {
            flag = new FlagEntity();
        } else {
            flag = flagDAO.getFlagById(id);
        }
    }

    public String delete() {
        if (flag.getId() == null) {
            return null;
        }

        try {
            flagDAO.delete(flag);
        } catch (Exception e) {
            log.error("Cannot delete author " + flag.getId(), e);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Cannot delete", "Cannot delete")
            );
            return null;
        }
        return "success";
    }

    public void save() {
        try {
            if (flag.getId() == null) {
                flagDAO.create(flag);
                MessagesHelper.addInfoMessage(null, "Successfully Created ");
            } else {
                flag = flagDAO.update(flag);
                MessagesHelper.addInfoMessage(null, "Successfully Updated ");
            }
        } catch (Exception e) {
            log.error("Cannot save shipyard " + flag.getId(), e);
            MessagesHelper.addInfoMessage(null, "Cannot delete");
        }
    }

    public void setId(long id) {
        this.id = id;
    }

    public FlagEntity getFlag() {
        return flag;
    }

    public long getId() {
        return id;
    }
}
