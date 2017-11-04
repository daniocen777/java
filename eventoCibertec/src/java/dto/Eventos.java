package dto;

import java.util.Date;

public class Eventos {

    private Integer idevento;
    private Integer idtipoevento;
    private String evento;
    private String descripcionevento;
    private String comienzo;
    private String fin;
    private String detalle;

    public Eventos() {
    }

    public Integer getIdevento() {
        return idevento;
    }

    public void setIdevento(Integer idevento) {
        this.idevento = idevento;
    }

    public Integer getIdtipoevento() {
        return idtipoevento;
    }

    public void setIdtipoevento(Integer idtipoevento) {
        this.idtipoevento = idtipoevento;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getDescripcionevento() {
        return descripcionevento;
    }

    public void setDescripcionevento(String descripcionevento) {
        this.descripcionevento = descripcionevento;
    }

    public String getComienzo() {
        return comienzo;
    }

    public void setComienzo(String comienzo) {
        this.comienzo = comienzo;
    }

    public String getFin() {
        return fin;
    }

    public void setFin(String fin) {
        this.fin = fin;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

}
