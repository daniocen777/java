package util;

import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Mensajes implements Serializable {

    // Métodos de apoyo - mensajes
    public void info(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void error(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void warning(String summary) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Advertencia", summary);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void errorMessage(String message) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "¡ERROR!", message));
    }

    public void growlMessageInfo(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "INFO", message));
    }

    public void growlMessageError(String message) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "ERROR", message));
    }

}
