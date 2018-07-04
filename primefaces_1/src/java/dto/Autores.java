package dto;

import java.io.Serializable;

public class Autores implements Serializable {

    private Integer idautor;
    private String autor;
    private String valor;

    public Autores() {
    }

    public Integer getIdautor() {
        return idautor;
    }

    public void setIdautor(Integer idautor) {
        this.idautor = idautor;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String valorCalculado() {
        if (autor.equalsIgnoreCase("daniel")) {
            valor = "Daniel";
        } else {
            valor = "No es DANIEL";
        }
        return valor;
    }

}
