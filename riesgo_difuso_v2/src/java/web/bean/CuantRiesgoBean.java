package web.bean;

import dao.DaoActEspec;
import dao.DaoActivo;
import dao.DaoCuantRiesgo;
import dao.DaoSalvaguarda;
import dao.impl.DaoActEspecImpl;
import dao.impl.DaoActivoImpl;
import dao.impl.DaoCuantRiesgoImpl;
import dao.impl.DaoSalvaguardaImpl;
import dto.ActEspec;
import dto.Activo;
import dto.CuantRiesgo;
import dto.Salvaguarda;
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
public class CuantRiesgoBean implements Serializable {

    private CuantRiesgo cuantRiesgo;
    private Salvaguarda salvaguarda;
    private List<CuantRiesgo> listCuantRiesgos;
    private List<Integer> checkCuantRiesgo = new ArrayList<>();
    private List<SelectItem> activosCbo;
    private Integer activoId; // Para el combo de activos
    private List<SelectItem> actEspecCbo; // Combo de activos específicos
    private Integer actEspecId;
    private List<SelectItem> amenazaCbo; // Combo de activos específicos
    private Integer amenazaId;
    private Mensajes mensajes;

    public CuantRiesgoBean() {
        cuantRiesgo = new CuantRiesgo();
        salvaguarda = new Salvaguarda();
        activoId = 0;
        actEspecId = 0;
        mensajes = new Mensajes();
    }

    public CuantRiesgo getCuantRiesgo() {
        return cuantRiesgo;
    }

    public void setCuantRiesgo(CuantRiesgo cuantRiesgo) {
        this.cuantRiesgo = cuantRiesgo;
    }

    public Salvaguarda getSalvaguarda() {
        return salvaguarda;
    }

    public void setSalvaguarda(Salvaguarda salvaguarda) {
        this.salvaguarda = salvaguarda;
    }

    public List<CuantRiesgo> getListCuantRiesgos() {
        DaoCuantRiesgo daoCuantRiesgo = new DaoCuantRiesgoImpl();
        listCuantRiesgos = daoCuantRiesgo.cuantRiesgosQry();
        return listCuantRiesgos;
    }

    public void setListCuantRiesgos(List<CuantRiesgo> listCuantRiesgos) {
        this.listCuantRiesgos = listCuantRiesgos;
    }

    public List<Integer> getCheckCuantRiesgo() {
        return checkCuantRiesgo;
    }

    public void setCheckCuantRiesgo(List<Integer> checkCuantRiesgo) {
        this.checkCuantRiesgo = checkCuantRiesgo;
    }

    public Mensajes getMensajes() {
        return mensajes;
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

    public Integer getActivoId() {
        return activoId;
    }

    public void setActivoId(Integer activoId) {
        this.activoId = activoId;
    }

    public List<SelectItem> getActEspecCbo() {
        this.actEspecCbo = new LinkedList<>();
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        List<ActEspec> listActEspec = daoActEspec.actEspecsQryXActivo(activoId);
        actEspecCbo.clear();
        listActEspec.stream().map((act) -> new SelectItem(act.getIdactespec(), act.getNombactespec())).forEachOrdered((selectItem) -> {
            this.actEspecCbo.add(selectItem);
        });
        return actEspecCbo;
    }

    public void setActEspecCbo(List<SelectItem> actEspecCbo) {
        this.actEspecCbo = actEspecCbo;
    }

    public Integer getActEspecId() {
        return actEspecId;
    }

    public void setActEspecId(Integer actEspecId) {
        this.actEspecId = actEspecId;
    }

    public List<SelectItem> getAmenazaCbo() {
        this.amenazaCbo = new LinkedList<>();
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        List<Salvaguarda> listAmenazas = daoSalvaguarda.salvaguardasQryXActEspec(activoId, actEspecId);
        amenazaCbo.clear();
        listAmenazas.stream().map((act) -> new SelectItem(act.getIdsalvaguarda(), act.getAmenaza().getAmenaza())).forEachOrdered((selectItem) -> {
            this.amenazaCbo.add(selectItem);
        });
        return amenazaCbo;
    }

    public void setAmenazaCbo(List<SelectItem> amenazaCbo) {
        this.amenazaCbo = amenazaCbo;
    }

    public Integer getAmenazaId() {
        return amenazaId;
    }

    public void setAmenazaId(Integer amenazaId) {
        this.amenazaId = amenazaId;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public void preparar() {
        cuantRiesgo = new CuantRiesgo();
        salvaguarda = new Salvaguarda();
//        activoId = null;
//        actEspecId = null;
    }

    // Métodp para llenar una lista con los IDs seleccionados para eliminar
    public void checkCuantRiesgo(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkCuantRiesgo.add(Integer.valueOf(check[i]));
        }
    }

    // Insertar
    public void cuantRiesgoIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoCuantRiesgo daoCuantRiesgo = new DaoCuantRiesgoImpl();
        cuantRiesgo.setSalvaguarda_idsalvaguarda(salvaguarda);
        String msj = daoCuantRiesgo.cuantRiesgosIns(cuantRiesgo);
        if (msj == null) {
            mensajes.growlMessageInfo("Se agregó el registro");
            requestContext.execute("PF('dialogCuantRiesgoIns').hide();");
        } else {
            mensajes.growlMessageError(daoCuantRiesgo.getMessage());
        }
    }

    public void pruebaId() {
        if (activoId == null) {
            mensajes.growlMessageError("NO HAY NADA");
        } else {
            mensajes.growlMessageInfo("" + activoId);
        }

    }

}
