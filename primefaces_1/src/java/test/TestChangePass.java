package test;

import dao.DaoUsuarios;
import dao.impl.DaoUsuariosImpl;
import dto.Usuarios;

public class TestChangePass {
    
    public static void main(String[] args) {
        
        Usuarios usuarios = new Usuarios();
        usuarios.setIdusuario(2);
        usuarios.setPassword("1234567");
        
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        String message = daoUsuarios.usuarioPassUpd(usuarios);
        
        if (message == null) {
            System.out.println("Actualización correcta");
        } else {
            System.out.println("Actualización INCORRECTA");
        }
    }
}
