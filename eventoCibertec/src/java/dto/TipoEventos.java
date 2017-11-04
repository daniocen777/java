package dto;

public class TipoEventos {

    private Integer idtipoevento;
    private String tipoevento;
    private String descripciontipo;

    public TipoEventos() {
    }

    public Integer getIdtipoevento() {
        return idtipoevento;
    }

    public void setIdtipoevento(Integer idtipoevento) {
        this.idtipoevento = idtipoevento;
    }

    public String getTipoevento() {
        return tipoevento;
    }

    public void setTipoevento(String tipoevento) {
        this.tipoevento = tipoevento;
    }

    public String getDescripciontipo() {
        return descripciontipo;
    }

    public void setDescripciontipo(String descripciontipo) {
        this.descripciontipo = descripciontipo;
    }

}
