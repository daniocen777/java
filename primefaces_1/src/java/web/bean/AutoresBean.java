package web.bean;

import dao.DaoAutores;
import dao.impl.DaoAutoresImpl;
import dto.Autores;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import util.Mensajes;
import web.validator.AutoresValidator;

@ManagedBean
@ViewScoped
public class AutoresBean implements Serializable {

    private Autores autores = new Autores();
    //private List<Object[]> listAutores;
    private List<Autores> listAutores;
    private Mensajes mensajes;
    private String label;

    public AutoresBean() {
        mensajes = new Mensajes();
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Autores> getListAutores() {
        DaoAutores daoAutores = new DaoAutoresImpl();
        listAutores = daoAutores.autoresQry2();
        return listAutores;
    }

    public void setListAutores(List<Autores> listAutores) {
        this.listAutores = listAutores;
    }

    // Preparar autor
    public void prepararAutor() {
        autores = new Autores();
    }

    // Insertar 
    public void autoresIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoAutores daoAutores = new DaoAutoresImpl();
        AutoresValidator autoresValidator = new AutoresValidator();
        List<String> list;
        list = autoresValidator.validarAutor(autores);

        if ((list.isEmpty())) {
            String msj = daoAutores.autoresIns(autores);
            if (msj == null) {
                mensajes.growlMessageInfo("Se agregó el autor " + autores.getAutor().toUpperCase());
                requestContext.execute("PF('dialogAutoresIns').hide();");
            } else {
                mensajes.growlMessageError(daoAutores.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }

    }

    // Get autor
    public Autores getAutor(Integer id) {
        DaoAutores daoAutores = new DaoAutoresImpl();
        autores = daoAutores.autoresGet(id);
        if (autores == null) {
            mensajes.error("Autor no encontrado");
        } else {
            mensajes.info("Se encontró el autor: " + autores.getAutor());
        }
        return autores;
    }

    //Actualizar autor
    public void autoresUpd() {
        DaoAutores daoAutores = new DaoAutoresImpl();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        AutoresValidator autoresValidator = new AutoresValidator();
        List<String> list;
        list = autoresValidator.validarAutor(autores);

        if ((list.isEmpty())) {
            String message = daoAutores.autoresUpd(autores);
            if (message == null) {
                requestContext.execute("PF('dialogAutoresUpd').hide();");
                mensajes.info("Se ha modificado el registro: " + autores.getAutor().toUpperCase());
                autores = new Autores();
            } else {
                mensajes.error(message);
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

    // Eliminar autores
    public void autoresDel() {
        DaoAutores daoAutores = new DaoAutoresImpl();
        String msg = daoAutores.autoresDel2(autores);
        if (msg != null) {
            mensajes.error(msg);
        } else {
            mensajes.info("Se eliminó el autor: " + autores.getAutor());
        }
    }

}
