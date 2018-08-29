package dao;

import dto.Usuarios;
import java.util.List;

public interface DaoUsuarios {

    public Usuarios autentica(String usuario, String password);

    // Listado de los usuarios por apellido
    public List<Usuarios> usuariosQry();

    // Método para insertar (ins) los usuarios
    public String usuarioIns(Usuarios usuarios);

    // Método para editar contraseña
    public String usuarioPassUpd(Usuarios usuarios);

    public String getMessage();
}
