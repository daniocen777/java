package web.validator;

import dao.DaoUsuarios;
import dao.impl.DaoUsuariosImpl;
import dto.Usuarios;
import java.util.LinkedList;
import java.util.List;

public class UsuarioValidator {

    public List<String> validarUsuario(Usuarios usuarios) {
        List<String> list = new LinkedList<>();
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        Usuarios objUsuario = daoUsuarios.autentica(usuarios.getUsuario(), usuarios.getPassword());
        if (objUsuario == null) {
            list.add("Usuario incorrecto");
        }

        return list;
    }
}
