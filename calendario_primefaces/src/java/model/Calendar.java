package model;

import java.io.Serializable;
import java.util.Date;

public class Calendar implements Serializable {

    private Integer id;
    private Date fecha;

    public Calendar() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
