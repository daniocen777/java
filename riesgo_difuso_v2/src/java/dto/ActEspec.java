/*
 Activo específico
 */
package dto;

import java.io.Serializable;

public class ActEspec implements Serializable {

    private Integer idactespec;
    private Activo activo;
    private String nombactespec;

    public ActEspec() {
    }

    public Integer getIdactespec() {
        return idactespec;
    }

    public void setIdactespec(Integer idactespec) {
        this.idactespec = idactespec;
    }

    public Activo getActivo() {
        return activo;
    }

    public void setActivo(Activo activo) {
        this.activo = activo;
    }

    public String getNombactespec() {
        return nombactespec;
    }

    public void setNombactespec(String nombactespec) {
        this.nombactespec = nombactespec;
    }

}
