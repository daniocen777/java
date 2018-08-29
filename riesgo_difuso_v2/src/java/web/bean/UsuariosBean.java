package web.bean;

import dao.DaoUsuarios;
import dao.impl.DaoUsuariosImpl;
import dto.Usuarios;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import util.Mensajes;

@ManagedBean
@RequestScoped
public class UsuariosBean implements Serializable {

    private Usuarios usuarios = new Usuarios();
    private List<Usuarios> listaUsuarios;
    private Mensajes mensajes;
    private String confirmPass;

    public UsuariosBean() {
        mensajes = new Mensajes();
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public List<Usuarios> getListaUsuarios() {
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        listaUsuarios = daoUsuarios.usuariosQry();
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public String getConfirmPass() {
        return confirmPass;
    }

    public void setConfirmPass(String confirmPass) {
        this.confirmPass = confirmPass;
    }

    // Insertar
    public void usuariosIns() {
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        String msj = daoUsuarios.usuarioIns(usuarios);
        if (msj.equals("ok")) {
            mensajes.growlMessageInfo("Usuario registrado correctamente");
            usuarios = new Usuarios();
        } else {
            mensajes.growlMessageError(daoUsuarios.getMessage());
        }
    }

}
