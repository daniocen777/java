package web.bean;

import dao.DaoAutores;
import dao.DaoFrases;
import dao.impl.DaoAutoresImpl;
import dao.impl.DaoFrasesImpl;
import dto.Autores;
import dto.Frases;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import util.Mensajes;
import web.validator.AutoresValidator;

@ManagedBean
@ViewScoped
public class FrasesBean implements Serializable {

    private Frases frases = new Frases();
    private Autores autores = new Autores();
    private List<Frases> listFrases;
    private Mensajes mensajes;
    private List<SelectItem> autoresCbo;
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String option;
    private List<String> options;

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public List<SelectItem> getAutoresCbo() {
        this.autoresCbo = new ArrayList<>();
        DaoAutores daoAutores = new DaoAutoresImpl();
        List<Autores> listAutores = daoAutores.autoresCbo2();
        autoresCbo.clear();
        for (Autores list : listAutores) {
            SelectItem selectItem = new SelectItem(list.getIdautor(), list.getAutor());
            this.autoresCbo.add(selectItem);
        }
        return autoresCbo;
    }

    public void setAutoresCbo(List<SelectItem> autoresCbo) {
        this.autoresCbo = autoresCbo;
    }

    public FrasesBean() {
        mensajes = new Mensajes();
        //options
        options = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            options.add("Option " + i);
        }
    }

    public Frases getFrases() {
        return frases;
    }

    public void setFrases(Frases frases) {
        this.frases = frases;
    }

    public List<Frases> getListFrases() {
        DaoFrases daoFrases = new DaoFrasesImpl();
        listFrases = daoFrases.frasesQry2();
        return listFrases;
    }

    public void setListFrases(List<Frases> listFrases) {
        this.listFrases = listFrases;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    // Preparar autor
    public void prepararFrase() {
        frases = new Frases();
        autores = new Autores();
    }

    public Autores getAutores() {
        return autores;
    }

    public void setAutores(Autores autores) {
        this.autores = autores;
    }

    public void frasesIns() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoFrases daoFrases = new DaoFrasesImpl();
        AutoresValidator autoresValidator = new AutoresValidator();
        List<String> list;
        autores.setIdautor(id);
        frases.setAutores(autores);
        list = autoresValidator.validarFrase(frases, autores);

        if ((list.isEmpty())) {
            String msj = daoFrases.frasesIns(frases);
            if (msj == null) {
                mensajes.growlMessageInfo("Se agregó la frase " + frases.getFrase().toUpperCase());
                requestContext.execute("PF('dialogFraseIns').hide();");
            } else {
                mensajes.growlMessageError(daoFrases.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }

    }

    // Editar frase
    public void frasesUpd() {
        RequestContext requestContext = RequestContext.getCurrentInstance();
        DaoFrases daoFrases = new DaoFrasesImpl();
        AutoresValidator autoresValidator = new AutoresValidator();
        List<String> list;
        autores.setIdautor(id);
        frases.setAutores(autores);
        list = autoresValidator.validarFrase(frases, autores);

        if ((list.isEmpty())) {
            String msj = daoFrases.frasesUpd(frases);
            if (msj == null) {
                mensajes.growlMessageInfo("Se editó la frase " + frases.getFrase().toUpperCase());
                requestContext.execute("PF('dialogFraseUpd').hide();");
                prepararFrase();
            } else {
                mensajes.growlMessageError(daoFrases.getMessage());
            }
        } else {
            for (String obj : list) {
                mensajes.growlMessageError(obj);
            }
        }

    }

    // Eliminar frase
    public void frasesDel() {
        DaoFrases daoFrases = new DaoFrasesImpl();
        String msg = daoFrases.frasesDel2(frases);
        if (msg != null) {
            mensajes.error(msg);
        } else {
            mensajes.info("Se eliminó la frase de " + frases.getAutores().getAutor() + ": " + frases.getFrase());
        }
    }

}
