package dto;

public class Activo {

    private Integer idactivo;
    private String nombact;
    private String tipoact;

    public Activo() {
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public String getNombact() {
        return nombact;
    }

    public void setNombact(String nombact) {
        this.nombact = nombact;
    }

    public String getTipoact() {
        return tipoact;
    }

    public void setTipoact(String tipoact) {
        this.tipoact = tipoact;
    }

}
