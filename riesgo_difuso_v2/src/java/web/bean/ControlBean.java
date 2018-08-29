package web.bean;

import dto.Usuarios;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class ControlBean implements Serializable {

    public ControlBean() {
    }

    public void verificarSesion() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            Usuarios usuarios = (Usuarios) facesContext.getExternalContext().getSessionMap().get("usuario");

            if (!(usuarios.getAutorizacion().equals("ADMIN"))) {
                //facesContext.getExternalContext().redirect("./../error.xhtml");
                facesContext.getExternalContext().redirect("./../client/userClient.xhtml");
            }

        } catch (Exception e) {
        }
    }

}
