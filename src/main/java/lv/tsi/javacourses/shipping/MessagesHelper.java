package lv.tsi.javacourses.shipping;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class MessagesHelper {

    public static void addInfoMessage(String clientId, String summary) {
        addMessage(clientId, FacesMessage.SEVERITY_INFO, summary);
    }

    public static void addErrorMessage(String clientId, String summary) {
        addMessage(clientId, FacesMessage.SEVERITY_ERROR, summary);
    }

    public static void addMessage(String clientId, FacesMessage.Severity severity, String summary) {
        var msg = new FacesMessage();
        msg.setSeverity(severity);
        msg.setSummary(summary);
        FacesContext.getCurrentInstance().addMessage(clientId, msg);
    }

}
