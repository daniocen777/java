package dto;

import java.io.Serializable;

public class Amenaza implements Serializable {

    private Integer idamenaza;
    private CatDeRiesgo catDeRiesgo;
    private String amenaza;
    private String consecuencia;

    public Amenaza() {
    }

    public Integer getIdamenaza() {
        return idamenaza;
    }

    public void setIdamenaza(Integer idamenaza) {
        this.idamenaza = idamenaza;
    }

    public CatDeRiesgo getCatDeRiesgo() {
        return catDeRiesgo;
    }

    public void setCatDeRiesgo(CatDeRiesgo catDeRiesgo) {
        this.catDeRiesgo = catDeRiesgo;
    }

    public String getAmenaza() {
        return amenaza;
    }

    public void setAmenaza(String amenaza) {
        this.amenaza = amenaza;
    }

    public String getConsecuencia() {
        return consecuencia;
    }

    public void setConsecuencia(String consecuencia) {
        this.consecuencia = consecuencia;
    }

}
