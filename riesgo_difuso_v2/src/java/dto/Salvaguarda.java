package dto;

import java.io.Serializable;

public class Salvaguarda implements Serializable {

    private Integer idsalvaguarda;
    private ActEspec actEspec;
    private Amenaza amenaza;
    private Responsable responsable;
    private String control;

    public Salvaguarda() {
    }

    public Integer getIdsalvaguarda() {
        return idsalvaguarda;
    }

    public void setIdsalvaguarda(Integer idsalvaguarda) {
        this.idsalvaguarda = idsalvaguarda;
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

    public String getControl() {
        return control;
    }

    public void setControl(String control) {
        this.control = control;
    }

}
