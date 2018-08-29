package web.bean;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import util.Mensajes;
import web.validator.ActivoValidator;

@ManagedBean
@ViewScoped
public class ActivoBean implements Serializable {

    private Activo activo;
    private List<Activo> listActivos;
    private Mensajes mensajes;
    private List<Integer> checkActivo = new ArrayList<>();
    private List<Activo> listActivoIns;

    public ActivoBean() {
        activo = new Activo();
        mensajes = new Mensajes();
        listActivoIns = new ArrayList<>();
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public List<Activo> getListActivos() {
        DaoActivo daoActivo = new DaoActivoImpl();
        listActivos = daoActivo.activosQry();
        return listActivos;
    }

    public void setListActivos(List<Activo> listActivos) {
        this.listActivos = listActivos;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public List<Integer> getCheckActivo() {
        return checkActivo;
    }

    public void setCheckActivo(List<Integer> checkActivo) {
        this.checkActivo = checkActivo;
    }

    public void prepararActivo() {
        activo = new Activo();
    }

    public List<Activo> getListActivoIns() {
        return listActivoIns;
    }

    public void setListActivoIns(List<Activo> listActivoIns) {
        this.listActivoIns = listActivoIns;
    }

    // Método para insertar activos
    public void activosIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoActivo daoActivo = new DaoActivoImpl();
        ActivoValidator activoValidator = new ActivoValidator();
        List<String> list;
        list = activoValidator.activoInsValidator(activo);

        if ((list.isEmpty())) {
            String msj = daoActivo.activosIns(activo);
            if (msj == null) {
                mensajes.growlMessageInfo("Se agregó el activo " + activo.getNombactivo().toUpperCase());
                requestContext.execute("PF('dialogActivosIns').hide();");
                prepararActivo();
            } else {
                mensajes.growlMessageError(daoActivo.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

    // Método para eliminar activo
    public void activosDel() {
        DaoActivo daoActivo = new DaoActivoImpl();
        String msg = daoActivo.activosDel(activo);
        if (msg != null) {
            mensajes.growlMessageError(msg);
        } else {
            mensajes.growlMessageWarnig("Se eliminó el activo: " + activo.getNombactivo());
            prepararActivo();
        }
    }

    // Eliminación múltiple
    public void activosDelMult() {
        DaoActivo daoActivo = new DaoActivoImpl();

        String msg = daoActivo.activoDelMult(checkActivo);
        if (msg.equals("ok")) {
            mensajes.growlMessageWarnig("Se eliminaron los registros");
            for (int i = 0; i < listActivos.size(); i++) {
                checkActivo.remove(listActivos.get(i).getIdactivo());
            }
        } else {
            for (int i = 0; i < listActivos.size(); i++) {
                checkActivo.remove(listActivos.get(i).getIdactivo());
            }
            mensajes.growlMessageError(msg);
        }

    }

    // Método que obliga al usuario a seleccionar obketos si quiere eliminar múltiples activos
    public void entrar() {
        if (checkActivo.isEmpty()) {
            mensajes.growlMessageError("Debe seleccionar uno o más activos");
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogActivosDelMult').show();");
        }
    }

    // Método para llenar una lista con los IDs seleccionados para eliminar
    public void checkActivo(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkActivo.add(Integer.valueOf(check[i]));
        }
    }

    // Editar activo
    public void activoUpd() {
        DaoActivo daoActivo = new DaoActivoImpl();
        RequestContext requestContext = RequestContext.getCurrentInstance();
        ActivoValidator activoValidator = new ActivoValidator();
        List<String> list;
        list = activoValidator.activoInsValidator(activo);

        if ((list.isEmpty())) {
            String message = daoActivo.activosUpd(activo);
            if (message.equals("ok")) {
                requestContext.execute("PF('dialogActivoUpd').hide();");
                mensajes.growlMessageInfo("Se ha modificado el registro: " + activo.getNombactivo().toUpperCase());
                prepararActivo();
            } else {
                mensajes.growlMessageError(message);
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

    // Crear
    public void createNew() {
        if (listActivoIns.contains(activo)) {
            mensajes.growlMessageError("Datos duplicados");
        } else {
            listActivoIns.add(activo);
            activo = new Activo();
        }
    }

    public String reinit() {
        prepararActivo();
        return null;
    }

    public void grabarMultiple() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoActivo daoActivo = new DaoActivoImpl();
        ActivoValidator activoValidator = new ActivoValidator();
        List<String> list;
        list = activoValidator.activoInsValidator(activo);

        if (listActivoIns.size() > 0) {
            if ((list.isEmpty())) {
                String msg = daoActivo.activosInsMult(listActivoIns);
                if (msg == null) {
                    requestContext.execute("PF('dialogActivosInsMult').hide();");
                    mensajes.growlMessageInfo("Se agregaron los activos");
                    listActivoIns.clear();
                } else {
                    mensajes.growlMessageError(msg);
                }
            } else {
                for (String obj : list) {
                    mensajes.growlMessageError(obj);
                }
            }

        } else {
            mensajes.growlMessageError("No ha ingresado activos");
        }

    }

}
