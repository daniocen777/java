package web.bean;

import dao.DaoActEspec;
import dao.DaoActivo;
import dao.impl.DaoActEspecImpl;
import dao.impl.DaoActivoImpl;
import dto.ActEspec;
import dto.Activo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import util.Mensajes;

@ManagedBean
@ViewScoped
public class ActEspecbean implements Serializable {

    private ActEspec actEspec;
    private Activo activo;
    private List<ActEspec> listActEspec;
    private List<SelectItem> activosCbo;
    private Integer id; // Para el combo de activos
    private List<Integer> checkActEspec = new ArrayList<>();
    private List<ActEspec> listActEspecIns;
    private Mensajes mensajes;

    public ActEspecbean() {
        actEspec = new ActEspec();
        activo = new Activo();
        listActEspecIns = new ArrayList<>();
        mensajes = new Mensajes();
    }

    public ActEspec getActEspec() {
        return actEspec;
    }

    public void setActEspec(ActEspec actEspec) {
        this.actEspec = actEspec;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public List<ActEspec> getListActEspec() {
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        listActEspec = daoActEspec.actEspecsQry();
        return listActEspec;
    }

    public void setListActEspec(List<ActEspec> listActEspec) {
        this.listActEspec = listActEspec;
    }

    public List<ActEspec> getListActEspecIns() {
        return listActEspecIns;
    }

    public void setListActEspecIns(List<ActEspec> listActEspecIns) {
        this.listActEspecIns = listActEspecIns;
    }

    public List<SelectItem> getActivosCbo() {
        this.activosCbo = new LinkedList<>();
        DaoActivo daoActivos = new DaoActivoImpl();
        List<Activo> listActivos = daoActivos.activosQry();
        activosCbo.clear();
        listActivos.stream().map((act) -> new SelectItem(act.getIdactivo(), act.getNombactivo())).forEachOrdered((selectItem) -> {
            this.activosCbo.add(selectItem);
        });
        return activosCbo;
    }

    public void setActivosCbo(List<SelectItem> activosCbo) {
        this.activosCbo = activosCbo;
    }

    public List<Integer> getCheckActEspec() {
        return checkActEspec;
    }

    public void setCheckActEspec(List<Integer> checkActEspec) {
        this.checkActEspec = checkActEspec;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    // Preparar activo específico
    public void prepararActEspec() {
        actEspec = new ActEspec();
        //activo = new Activo();
        id = null;
    }

    // Método que obliga al usuario a seleccionar obketos si quiere eliminar múltiples activos
    public void entrar() {
        if (checkActEspec.isEmpty()) {
            mensajes.growlMessageError("Debe seleccionar uno o más activos específicos");
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogActivoEspecDelMult').show();");
        }
    }

    // Métodp para llenar una lista con los IDs seleccionados para eliminar
    public void checkActEspec(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkActEspec.add(Integer.valueOf(check[i]));
        }
    }

    // Eliminar activo específico
    public void actEspecDel() {
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        String msg = daoActEspec.actEspecsDel(actEspec);
        if (msg != null) {
            mensajes.growlMessageError(msg);
        } else {
            mensajes.growlMessageWarnig("Se eliminó el activo específico de "
                    + actEspec.getActivo().getNombactivo() + ": "
                    + actEspec.getNombactespec());
        }
    }

    // Eliminar activos específocs masivamente
    public void actEspecDelMult() {
        DaoActEspec daoActEspec = new DaoActEspecImpl();

        String msg = daoActEspec.actEspecDelMult(checkActEspec);
        if (msg == null) {
            mensajes.growlMessageWarnig("Se eliminaron los registros");
            for (int i = 0; i < listActEspec.size(); i++) {
                checkActEspec.remove(listActEspec.get(i).getIdactespec());
            }
        } else {
            for (int i = 0; i < listActEspec.size(); i++) {
                checkActEspec.remove(listActEspec.get(i).getIdactespec());
            }
            mensajes.growlMessageError(msg);
        }
    }

    public String reinit() {
        DaoActivo daoActivo = new DaoActivoImpl();
        activo.setIdactivo(id);
        activo.setNombactivo(daoActivo.activoXId(id));
        actEspec.setActivo(activo);
        activo = new Activo();
        actEspec = new ActEspec();
        return null;
    }

    // Agregar activo específico
    public void actEspecIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        activo.setIdactivo(id);
        actEspec.setActivo(activo);

        String msj = daoActEspec.actEspecsIns(actEspec);
        if (msj == null) {
            mensajes.growlMessageInfo("Se agregó el registro " + actEspec.getNombactespec().toUpperCase());
            requestContext.execute("PF('dialogActEspecIns').hide();");
            id = null;
        } else {
            mensajes.growlMessageError(daoActEspec.getMessage());
        }
    }

    // Insertar masivamente
    public void actEspecInsMult() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoActEspec daoActEspec = new DaoActEspecImpl();

        activo.setIdactivo(id);
        actEspec.setActivo(activo);

        if (listActEspecIns.size() > 0) {

            String msg = daoActEspec.actEspecsInsMult(listActEspecIns);
            if (msg == null) {
                requestContext.execute("PF('dialogActEspecInsMult').hide();");
                mensajes.growlMessageInfo("Se agregaron los registros correctamente");
                id = null;
                listActEspecIns.clear();
            } else {
                mensajes.growlMessageError(msg);
            }

        } else {
            mensajes.growlMessageError("No ha ingresado activos");
        }
    }

    // Editar activo específico
    public void actEspecUpd() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        activo.setIdactivo(id);
        actEspec.setActivo(activo);

        String msj = daoActEspec.actEspecsUpd(actEspec);
        if (msj == null) {
            mensajes.growlMessageInfo("Se editó el registro " + actEspec.getNombactespec().toUpperCase());
            requestContext.execute("PF('dialogActEspecUpd').hide();");
            id = null;
            prepararActEspec();
        } else {
            mensajes.growlMessageError(daoActEspec.getMessage());
        }
    }
}
