package dto;

import java.io.Serializable;

public class Activo implements Serializable {

    private Integer idactivo;
    private String nombactivo;
    private String tipo;

    public Activo() {
    }

    public Integer getIdactivo() {
        return idactivo;
    }

    public void setIdactivo(Integer idactivo) {
        this.idactivo = idactivo;
    }

    public String getNombactivo() {
        return nombactivo;
    }

    public void setNombactivo(String nombactivo) {
        this.nombactivo = nombactivo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

}
