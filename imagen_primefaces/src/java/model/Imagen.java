package model;

import org.primefaces.model.UploadedFile;

public class Imagen {

    private Integer id;
    private UploadedFile img;

    public Imagen() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

}
