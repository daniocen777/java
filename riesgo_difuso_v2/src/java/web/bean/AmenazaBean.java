package web.bean;

import dao.DaoAmenaza;
import dao.DaoCatDeRiesgo;
import dao.impl.DaoAmenazaImpl;
import dao.impl.DaoCatDeRiesgoImpl;
import dto.Amenaza;
import dto.CatDeRiesgo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ValueChangeEvent;
import org.primefaces.context.RequestContext;
import org.primefaces.model.DualListModel;
import util.Mensajes;
import web.validator.AmenazaValidator;

@ManagedBean
@ViewScoped
public class AmenazaBean implements Serializable {

    private Amenaza amenaza;
    private Mensajes mensajes;
    private List<Amenaza> listAmenaza;
    private CatDeRiesgo catDeRiesgo;
    private Integer id; // 
    private List<Integer> checkAmenaza = new ArrayList<>();
    private DualListModel<CatDeRiesgo> categorias;

    public AmenazaBean() {
        amenaza = new Amenaza();
        catDeRiesgo = new CatDeRiesgo();
        mensajes = new Mensajes();
    }

    public Amenaza getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(Amenaza amenaza) {
        this.amenaza = amenaza;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public List<Integer> getCheckAmenaza() {
        return checkAmenaza;
    }

    public void setCheckAmenaza(List<Integer> checkAmenaza) {
        this.checkAmenaza = checkAmenaza;
    }

    public CatDeRiesgo getCatDeRiesgo() {
        return catDeRiesgo;
    }

    public void setCatDeRiesgo(CatDeRiesgo catDeRiesgo) {
        this.catDeRiesgo = catDeRiesgo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void prepararAmenaza() {
        amenaza = new Amenaza();
    }

    public DualListModel<CatDeRiesgo> getCategorias() {
        DaoCatDeRiesgo daoCatDeRiesgo = new DaoCatDeRiesgoImpl();
        if (categorias == null) {
            categorias = new DualListModel<>();
        }
        categorias.setSource(daoCatDeRiesgo.catDeRiesgoQry());
        return categorias;
    }

    public void setCategorias(DualListModel<CatDeRiesgo> categorias) {
        this.categorias = categorias;
    }

    public List<Amenaza> getListAmenaza() {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        listAmenaza = daoAmenaza.amenazasQry();
        return listAmenaza;
    }

    public void setListAmenaza(List<Amenaza> listAmenaza) {
        this.listAmenaza = listAmenaza;
    }

    // Eliminar amenazas masivamente
    public void amenazaDelMult() {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();

        String msg = daoAmenaza.amenazaDelMult(checkAmenaza);
        if (msg == null) {
            mensajes.growlMessageWarnig("Se eliminaron los registros");
            for (int i = 0; i < listAmenaza.size(); i++) {
                checkAmenaza.remove(listAmenaza.get(i).getIdamenaza());
            }
        } else {
            for (int i = 0; i < listAmenaza.size(); i++) {
                checkAmenaza.remove(listAmenaza.get(i).getIdamenaza());
            }
            mensajes.growlMessageError(msg);
        }
    }

    // Método que obliga al usuario a seleccionar obketos si quiere eliminar múltiples activos
    public void entrar() {
        if (checkAmenaza.isEmpty()) {
            mensajes.growlMessageError("Debe seleccionar uno o más elementos");
        } else {
            RequestContext requestContext = RequestContext.getCurrentInstance();
            requestContext.execute("PF('dialogAmenazaDelMult').show();");
        }
    }

    // Métodp para llenar una lista con los IDs seleccionados para eliminar
    public void checkAmenaza(ValueChangeEvent event) {
        String[] check = (String[]) event.getNewValue();
        for (int i = 0; i < check.length; i++) {
            checkAmenaza.add(Integer.valueOf(check[i]));
        }
    }

    // Insertar
    public void amenazaIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        AmenazaValidator amenazaValidator = new AmenazaValidator();

        catDeRiesgo.setIdcatderiesgo(id);
        amenaza.setCatDeRiesgo(catDeRiesgo);
        List<String> list = amenazaValidator.amenazaValidatorIns(amenaza);

        if (list.isEmpty()) {
            String msj = daoAmenaza.amenazasIns(amenaza);

            if (msj == null) {
                mensajes.growlMessageInfo("Se agregó el registro " + amenaza.getAmenaza().toUpperCase());
                requestContext.execute("PF('dialogAmenazaIns').hide();");
                id = null;
            } else {
                mensajes.growlMessageError(daoAmenaza.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }

    }

    // Eliminar 1 a la vez
    public void amenazaDel() {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        String msg = daoAmenaza.amenazasDel(amenaza);
        if (msg != null) {
            mensajes.growlMessageError(msg);
        } else {
            mensajes.growlMessageWarnig("Se eliminó el registro "
                    + amenaza.getAmenaza().toUpperCase());
        }
    }

    // Editar
    public void amenazaUpd() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        AmenazaValidator amenazaValidator = new AmenazaValidator();
        catDeRiesgo.setIdcatderiesgo(id);
        amenaza.setCatDeRiesgo(catDeRiesgo);
        List<String> list = amenazaValidator.amenazaValidatorIns(amenaza);

        if (list.isEmpty()) {
            String msj = daoAmenaza.amenazasUpd(amenaza);

            if (msj == null) {
                mensajes.growlMessageInfo("Se actualizó el registro " + amenaza.getAmenaza().toUpperCase());
                requestContext.execute("PF('dialogAmenazaUpd').hide();");
                id = null;
            } else {
                mensajes.growlMessageError(daoAmenaza.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }
    }

}
