package web.bean;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DualListModel;
import util.Mensajes;

@ManagedBean
@ViewScoped
public class PruebaBean {

    private Activo activo;
    private Mensajes mensajes;

    private DualListModel<Activo> activos;

    public PruebaBean() {
        mensajes = new Mensajes();
        //activo = new Activo();

    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public DualListModel<Activo> getActivos() {
        DaoActivo daoActivo = new DaoActivoImpl();
        if (activos == null) {
            activos = new DualListModel<>();
        }
        activos.setSource(daoActivo.activosQry());
        return activos;
    }

    public void setActivos(DualListModel<Activo> activos) {
        this.activos = activos;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public void mostrar() {
        mensajes.growlMessageInfo(activos.getTarget().toString());
        mensajes.growlMessageInfo(activos.getSource().toString());

    }

//    public DualListModel<Activo> listaDual() {
//        DaoActivo daoActivo = new DaoActivoImpl();
//        activosSource = daoActivo.activosQry();
//        activos = new DualListModel<>(activosSource, activosTarget);
//        return activos;
//    }
}
