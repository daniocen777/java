package test.usuarios;

import dao.DaoUsuarios;
import dao.impl.DaoUsuariosImpl;
import dto.Usuarios;


public class Autentica {
    
    public static void main(String[] args) {
        DaoUsuarios daoUsuarios = new DaoUsuariosImpl();
        Usuarios usuarios;
        usuarios = daoUsuarios.autentica("cliente", "cliente");
        
        System.out.println("ID: " + usuarios.getIdusuario() + " -- "
        + "Usuario: " + usuarios.getUsuario() + " -- " + "Autent.: " + usuarios.getAutorizacion());
    }
}
