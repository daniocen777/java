package dto;

import java.io.Serializable;

public class CatDeRiesgo implements Serializable {

    private Integer idcatderiesgo;
    private String categoria;
    private String desc;

    public CatDeRiesgo() {
    }

    public Integer getIdcatderiesgo() {
        return idcatderiesgo;
    }

    public void setIdcatderiesgo(Integer idcatderiesgo) {
        this.idcatderiesgo = idcatderiesgo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
