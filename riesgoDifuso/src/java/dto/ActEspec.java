package dto;

// Clase ActEspec ==> Activo Específico (Unida a Activo por campo "idactivo")
public class ActEspec {

    private Integer idactespec;
    private Integer idactivo;
    private String nombactespec;

    public ActEspec() {
    }

    public Integer getIdactespec() {
        return idactespec;
    }

    public void setIdactespec(Integer idactespec) {
        this.idactespec = idactespec;
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public String getNombactespec() {
        return nombactespec;
    }

    public void setNombactespec(String nombactespec) {
        this.nombactespec = nombactespec;
    }

}
