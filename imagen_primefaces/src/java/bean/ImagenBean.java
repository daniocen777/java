package bean;

import dao.ImagenDao;
import dao.impl.ImagenDaoImpl;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import model.Imagen;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class ImagenBean {

    private Imagen imagen;
    private UploadedFile file;

    public ImagenBean() {
        imagen = new Imagen();
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void grabar() {
        try {
            ImagenDao imagenDao = new ImagenDaoImpl();
            imagen.setImg(file);
            String message = imagenDao.imagenIns(imagen);
            if (message == null) {
                FacesMessage msj = new FacesMessage("Correcto", "Imagen subida");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            } else {
                FacesMessage msj = new FacesMessage("ERROR", "Imagen NO subida");
                FacesContext.getCurrentInstance().addMessage(null, msj);
            }
        } catch (Exception e) {
            FacesMessage msj = new FacesMessage("ERROR", "Imagen NO subida");

            FacesContext.getCurrentInstance().addMessage(null, msj);
        }

    }

}
