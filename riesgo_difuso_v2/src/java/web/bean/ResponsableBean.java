package web.bean;

import dao.DaoResponsable;
import dao.impl.DaoResponsableImpl;
import dto.Responsable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import util.Mensajes;
import web.validator.ResponsableValidator;

@ManagedBean
@ViewScoped
public class ResponsableBean implements Serializable {

    private Responsable responsable = new Responsable();
    ;
    private List<Responsable> listResponsables;
    private Mensajes mensajes;
    private List<Integer> checkResponsables = new ArrayList<>();

    public ResponsableBean() {
        mensajes = new Mensajes();
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public List<Responsable> getListResponsables() {
        DaoResponsable daoResponsable = new DaoResponsableImpl();
        listResponsables = daoResponsable.responsablesQry();
        return listResponsables;
    }

    public void setListResponsables(List<Responsable> listResponsables) {
        this.listResponsables = listResponsables;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public List<Integer> getCheckResponsables() {
        return checkResponsables;
    }

    public void setCheckResponsables(List<Integer> checkResponsables) {
        this.checkResponsables = checkResponsables;
    }

    public void prepararResponsable() {
        responsable = new Responsable();
    }

    // Método para insertar responsables
    public void responsablesIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoResponsable daoResponsable = new DaoResponsableImpl();
        ResponsableValidator responsableValidator = new ResponsableValidator();
        List<String> list;
        list = responsableValidator.responsableInsValidator(responsable);

        if ((list.isEmpty())) {
            String msj = daoResponsable.responsablesIns(responsable);
            if (msj == null) {
                mensajes.growlMessageInfo("Se agregó el responsable " + responsable.getNombre().toUpperCase());
                requestContext.execute("PF('dialogResponsableIns').hide();");
                prepararResponsable();
            } else {
                mensajes.growlMessageError(daoResponsable.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

    // Control para eliminar un responsable
    public void responsableDel() {
        DaoResponsable daoResponsable = new DaoResponsableImpl();
        String msg = daoResponsable.responsablesDel(responsable);
        if (msg == null) {
            mensajes.growlMessageWarnig("Se eliminó a: " + responsable.getNombre());
            prepararResponsable();
        } else {
            mensajes.growlMessageError(msg);
        }
    }

    // Método para editar responsable
    public void responsableUpd() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoResponsable daoResponsable = new DaoResponsableImpl();
        ResponsableValidator responsableValidator = new ResponsableValidator();
        List<String> list;
        list = responsableValidator.responsableInsValidator(responsable);

        if ((list.isEmpty())) {
            String msj = daoResponsable.responsablesUpd(responsable);
            if (msj == null) {
                mensajes.growlMessageInfo("Se modificaron datos de " + responsable.getNombre().toUpperCase());
                requestContext.execute("PF('dialogResponsableUpd').hide();");
                prepararResponsable();
            } else {
                mensajes.growlMessageError(daoResponsable.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

    // Métodp para llenar una lista con los IDs seleccionados para eliminar
    public void checkResponsables(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkResponsables.add(Integer.valueOf(check[i]));
        }
    }

    // Método que obliga al usuario a seleccionar obketos si quiere eliminar múltiples activos
    public void entrar() {
        if (checkResponsables.isEmpty()) {
            mensajes.growlMessageError("Debe seleccionar uno o más responssables");
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogResponsablesDelMult').show();");
        }
    }

    // Eliminación multiple
    public void responsablesDelMult() {
        DaoResponsable daoResponsable = new DaoResponsableImpl();

        String msg = daoResponsable.responsableDelMult(checkResponsables);
        if (msg == null) {
            mensajes.growlMessageWarnig("Se eliminaron los registros");
            for (int i = 0; i < listResponsables.size(); i++) {
                checkResponsables.remove(listResponsables.get(i).getIdresponsable());
            }
        } else {
            for (int i = 0; i < listResponsables.size(); i++) {
                checkResponsables.remove(listResponsables.get(i).getIdresponsable());
            }
            mensajes.growlMessageError(msg);
        }

    }

}
