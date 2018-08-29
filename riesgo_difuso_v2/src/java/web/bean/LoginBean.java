package web.bean;

import dao.DaoUsuarios;
import dao.impl.DaoUsuariosImpl;
import dto.Usuarios;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import util.Mensajes;
import web.validator.UsuarioValidator;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {

    private String username;
    private String password;
    private Usuarios usuarios;
    private Mensajes mensajes;
    private List<Usuarios> listUsuarios;
    private boolean visible;
    private boolean visiblePrincipal = true;
    private String passwordConfirm;

    public List<Usuarios> getListUsuarios() {
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        listUsuarios = daoUsuarios.usuariosQry();
        return listUsuarios;
    }

    public void show() {
        visible = true;
    }

    public void hide() {
        visible = false;
    }

    public boolean isVisible() {
        return visible;
    }

    public boolean isVisiblePrincipal() {
        return visiblePrincipal;
    }

    public void setVisiblePrincipal(boolean visiblePrincipal) {
        this.visiblePrincipal = visiblePrincipal;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setListUsuarios(List<Usuarios> listUsuarios) {
        this.listUsuarios = listUsuarios;
    }

    public LoginBean() {
        usuarios = new Usuarios();
        mensajes = new Mensajes();
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public Mensajes getMensajes() {
        return mensajes;
    }

    public void setMensajes(Mensajes mensajes) {
        this.mensajes = mensajes;
    }

    public Usuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuarios usuarios) {
        this.usuarios = usuarios;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        boolean loggedIn = false;
        String ruta = "";
        UsuarioValidator usuarioValidator = new UsuarioValidator();
        List<String> list;
        usuarios.setUsuario(username);
        usuarios.setPassword(password);
        Usuarios u = daoUsuarios.autentica(username, password);
        //list = usuarioValidator.validarUsuario(usuarios);

        if (u != null) {
            if (usuarios.getUsuario().equals(usuarios.getPassword())) {
                visible = true;
                visiblePrincipal = false;
            } else {
                visible = false;
                visiblePrincipal = true;
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);

                loggedIn = true;
                ruta = "/riesgo_difuso_v2/faces/admin/index.xhtml";
            }

        } else {
            mensajes.growlMessageError("No hay acceso");
            loggedIn = false;
            usuarios = new Usuarios();
        }

        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);

    }

    // Cerrar sesión
    public String cerrarSesion() {
        username = null;
        password = null;

        HttpSession httpSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        httpSession.invalidate();
        return "/login";
    }

    public void changePass() {
        if (usuarios.getPassword().equals(passwordConfirm)) {
            DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
            String message = daoUsuarios.usuarioPassUpd(usuarios);
            if (message == null) {
                mensajes.growlMessageInfo("Ingrese con su nueva contraseña");
                visible = false;
                visiblePrincipal = true;
            } else {
                mensajes.growlMessageError(message);
            }
        } else {
            mensajes.growlMessageError("Error de confirmación");
        }

    }

}
