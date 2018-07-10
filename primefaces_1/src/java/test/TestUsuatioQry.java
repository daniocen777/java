package test;

import dao.DaoUsuarios;
import dao.impl.DaoUsuariosImpl;
import dto.Usuarios;
import java.util.List;

public class TestUsuatioQry {
    
    public static void main(String[] args) {
        
        DaoUsuarios daoUser = new DaoUsuariosImpl();
        
        List<Usuarios> list = daoUser.usuariosQry();
        
        for (Usuarios u : list) {
            System.out.println(u.getUsuario() + " - " + u.getIdusuario());
        }
    }
}
