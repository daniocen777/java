package web.bean;

import dao.DaoActEspec;
import dao.DaoAmenaza;
import dao.DaoResponsable;
import dao.DaoSalvaguarda;
import dao.impl.DaoActEspecImpl;
import dao.impl.DaoAmenazaImpl;
import dao.impl.DaoResponsableImpl;
import dao.impl.DaoSalvaguardaImpl;
import dto.ActEspec;
import dto.Amenaza;
import dto.Responsable;
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
import org.primefaces.model.DualListModel;
import util.Mensajes;
import web.validator.SalvaguardaValidator;

@ManagedBean
@ViewScoped
public class SalvaguardaBean implements Serializable {

    private Salvaguarda salvaguarda;
    private ActEspec actEspec;
    private Amenaza amenaza;
    private Responsable responsable;
    private List<Salvaguarda> listSalvaguarda; // Lista de salvaguardas
    private List<Integer> checkSalvaguardas = new ArrayList<>(); // Eliminar múltiple
    private List<SelectItem> actEspecCbo; // Combo de activos específicos
    private List<SelectItem> responsablesCbo; // Combo de responsables
    private List<SelectItem> amenazasCbo; // Combo de amenazas
    private Integer idActEspec;
    private Integer idResponsable;
    private Integer idAmenaza;
    private DualListModel<Amenaza> amenazasPickList; // Amenazas pickList
    private DualListModel<Responsable> responsablePickList;
    private Mensajes mensajes;

    public SalvaguardaBean() {
        salvaguarda = new Salvaguarda();
        actEspec = new ActEspec();
        amenaza = new Amenaza();
        responsable = new Responsable();
        mensajes = new Mensajes();
    }

    public Salvaguarda getSalvaguarda() {
        return salvaguarda;
    }

    public void setSalvaguarda(Salvaguarda salvaguarda) {
        this.salvaguarda = salvaguarda;
    }

    public ActEspec getActEspec() {
        return actEspec;
    }

    public void setActEspec(ActEspec actEspec) {
        this.actEspec = actEspec;
    }

    public Amenaza getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(Amenaza amenaza) {
        this.amenaza = amenaza;
    }

    public Responsable getResponsable() {
        return responsable;
    }

    public void setResponsable(Responsable responsable) {
        this.responsable = responsable;
    }

    public List<Salvaguarda> getListSalvaguarda() {
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        listSalvaguarda = daoSalvaguarda.salvaguardasQry();
        return listSalvaguarda;
    }

    public void setListSalvaguarda(List<Salvaguarda> listSalvaguarda) {
        this.listSalvaguarda = listSalvaguarda;
    }

    public List<Integer> getCheckSalvaguardas() {
        return checkSalvaguardas;
    }

    public void setCheckSalvaguardas(List<Integer> checkSalvaguardas) {
        this.checkSalvaguardas = checkSalvaguardas;
    }

    // Método que obliga al usuario a seleccionar obketos si quiere eliminar múltiples activos
    public void entrar() {
        if (checkSalvaguardas.isEmpty()) {
            mensajes.growlMessageError("Debe seleccionar uno o más elementos");
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogSalvaguardaDelMult').show();");
        }
    }

    // Métodp para llenar una lista con los IDs seleccionados para eliminar
    public void checkSalvaguardas(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkSalvaguardas.add(Integer.valueOf(check[i]));
        }
    }

    public List<SelectItem> getActEspecCbo() {
        this.actEspecCbo = new LinkedList<>();
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        List<ActEspec> listActEspec = daoActEspec.actEspecsQry();
        actEspecCbo.clear();
        listActEspec.stream().map((act) -> new SelectItem(act.getIdactespec(), act.getActivo().getNombactivo() + " - " + act.getNombactespec())).forEachOrdered((selectItem) -> {
            this.actEspecCbo.add(selectItem);
        });
        return actEspecCbo;
    }

    public void setActEspecCbo(List<SelectItem> actEspecCbo) {
        this.actEspecCbo = actEspecCbo;
    }

    public List<SelectItem> getResponsablesCbo() {
        this.responsablesCbo = new LinkedList<>();
        DaoResponsable daoResponsable = new DaoResponsableImpl();
        List<Responsable> listResponsables = daoResponsable.responsablesQry();
        responsablesCbo.clear();
        listResponsables.stream().map((act) -> new SelectItem(act.getIdresponsable(), act.getNombre())).forEachOrdered((selectItem) -> {
            this.responsablesCbo.add(selectItem);
        });
        return responsablesCbo;
    }

    public void setResponsablesCbo(List<SelectItem> responsablesCbo) {
        this.responsablesCbo = responsablesCbo;
    }

    public List<SelectItem> getAmenazasCbo() {
        this.amenazasCbo = new LinkedList<>();
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        List<Amenaza> listAmenazas = daoAmenaza.amenazasQry();
        amenazasCbo.clear();
        listAmenazas.stream().map((act) -> new SelectItem(act.getIdamenaza(), act.getAmenaza())).forEachOrdered((selectItem) -> {
            this.amenazasCbo.add(selectItem);
        });
        return amenazasCbo;
    }

    public void setAmenazasCbo(List<SelectItem> amenazasCbo) {
        this.amenazasCbo = amenazasCbo;
    }

    public Integer getIdActEspec() {
        return idActEspec;
    }

    public void setIdActEspec(Integer idActEspec) {
        this.idActEspec = idActEspec;
    }

    public Integer getIdResponsable() {
        return idResponsable;
    }

    public void setIdResponsable(Integer idResponsable) {
        this.idResponsable = idResponsable;
    }

    public Integer getIdAmenaza() {
        return idAmenaza;
    }

    public void setIdAmenaza(Integer idAmenaza) {
        this.idAmenaza = idAmenaza;
    }

    public DualListModel<Amenaza> getAmenazasPickList() {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        if (amenazasPickList == null) {
            amenazasPickList = new DualListModel<>();
        }
        amenazasPickList.setSource(daoAmenaza.amenazasQry());
        return amenazasPickList;
    }

    public void setAmenazasPickList(DualListModel<Amenaza> amenazasPickList) {
        this.amenazasPickList = amenazasPickList;
    }

    public DualListModel<Responsable> getResponsablePickList() {
        DaoResponsable daoResponsable = new DaoResponsableImpl();
        if (responsablePickList == null) {
            responsablePickList = new DualListModel<>();
        }
        responsablePickList.setSource(daoResponsable.responsablesQry());
        return responsablePickList;
    }

    public void setResponsablePickList(DualListModel<Responsable> responsablePickList) {
        this.responsablePickList = responsablePickList;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    // Preparar objetos
    public void preparar() {
        salvaguarda = new Salvaguarda();
        actEspec = new ActEspec();
        amenaza = new Amenaza();
        responsable = new Responsable();
        idActEspec = null;
        idAmenaza = null;
        idResponsable = null;

    }

    // Insertar
    public void salvaguerdaIns() {
        String msg;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        actEspec.setIdactespec(idActEspec);
        amenaza.setIdamenaza(idAmenaza);
        responsable.setIdresponsable(idResponsable);
        salvaguarda.setActEspec(actEspec);
        salvaguarda.setAmenaza(amenaza);
        salvaguarda.setResponsable(responsable);
        SalvaguardaValidator salvaguardaValidator = new SalvaguardaValidator();
        List<String> list = salvaguardaValidator.salvaguardaInsValidator(salvaguarda);

        if (list.isEmpty()) {
            msg = daoSalvaguarda.salvaguardasIns(salvaguarda);
            //responsable = new Responsable();
            if (msg == null) {
                requestContext.execute("PF('dialogSalvaguardaIns').hide();");
                mensajes.growlMessageInfo("Registro agregado correctamente");
            } else {
                mensajes.growlMessageError(daoSalvaguarda.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }

//        if (list.isEmpty()) {
//            for (int i = 0; i < responsablePickList.getTarget().size(); i++) {
//                responsable.setIdresponsable(DeString.toInteger(responsablePickList.getTarget().toArray()[i].toString()));
//                salvaguarda.setResponsable(responsable);
//                msg = daoSalvaguarda.salvaguardasIns(salvaguarda);
//                //responsable = new Responsable();
//            }
//            if (msg == null) {
//                requestContext.execute("PF('dialogSalvaguardaIns').hide();");
//                mensajes.growlMessageInfo("Registro guardado correctamente");
//            } else {
//                mensajes.growlMessageError(daoSalvaguarda.getMessage());
//            }
//        } else {
//            for (String obj : list) {
//                mensajes.growlMessageError(obj);
//            }
//        }
    }

    // Eliminar
    public void salvaguardaDel() {
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        String msg = daoSalvaguarda.salvaguardasDel(salvaguarda);

        if (msg != null) {
            mensajes.growlMessageError(msg);
        } else {
            mensajes.growlMessageWarnig("Registro eliminado correctamente");
        }
    }

    // Elimianr masivamente
    public void salvaguardaDelMult() {
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        String msg = daoSalvaguarda.salvaguardaDelMult(checkSalvaguardas);
        if (msg == null) {
            mensajes.growlMessageWarnig("Se eliminaron los registros");
            for (int i = 0; i < listSalvaguarda.size(); i++) {
                checkSalvaguardas.remove(listSalvaguarda.get(i).getIdsalvaguarda());
            }
        } else {
            for (int i = 0; i < listSalvaguarda.size(); i++) {
                checkSalvaguardas.remove(listSalvaguarda.get(i).getIdsalvaguarda());
            }
            mensajes.growlMessageError(msg);
        }
    }

    // Editar
    public void salvaguardaUpd() {
        String msg;
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        actEspec.setIdactespec(idActEspec);
        amenaza.setIdamenaza(idAmenaza);
        responsable.setIdresponsable(idResponsable);
        salvaguarda.setActEspec(actEspec);
        salvaguarda.setAmenaza(amenaza);
        salvaguarda.setResponsable(responsable);
        SalvaguardaValidator salvaguardaValidator = new SalvaguardaValidator();
        List<String> list = salvaguardaValidator.salvaguardaInsValidator(salvaguarda);

        if (list.isEmpty()) {
            msg = daoSalvaguarda.salvaguardasUpd(salvaguarda);
            //responsable = new Responsable();
            if (msg == null) {
                requestContext.execute("PF('dialogSalvaguardaUpd').hide();");
                mensajes.growlMessageInfo("Registro editado correctamente");
            } else {
                mensajes.growlMessageError(daoSalvaguarda.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

}
