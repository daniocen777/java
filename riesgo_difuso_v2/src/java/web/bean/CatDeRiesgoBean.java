package web.bean;

import dao.DaoCatDeRiesgo;
import dao.impl.DaoCatDeRiesgoImpl;
import dto.CatDeRiesgo;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class CatDeRiesgoBean implements Serializable {

    private CatDeRiesgo catDeRiesgo;
    private List<CatDeRiesgo> listCatDeRiesgo;

    public CatDeRiesgoBean() {
        catDeRiesgo = new CatDeRiesgo();
    }

    public CatDeRiesgo getCatDeRiesgo() {
        return catDeRiesgo;
    }

    public void setCatDeRiesgo(CatDeRiesgo catDeRiesgo) {
        this.catDeRiesgo = catDeRiesgo;
    }

    public List<CatDeRiesgo> getListCatDeRiesgo() {
        DaoCatDeRiesgo daoCatDeRiesgo = new DaoCatDeRiesgoImpl();
        listCatDeRiesgo = daoCatDeRiesgo.catDeRiesgoQry();
        return listCatDeRiesgo;
    }

    public void setListCatDeRiesgo(List<CatDeRiesgo> listCatDeRiesgo) {
        this.listCatDeRiesgo = listCatDeRiesgo;
    }

}
