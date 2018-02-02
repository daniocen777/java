package test;

import dao.impl.DaoActEspecImpl;

public class TestActEspecGet2 {
    
    public static void main(String[] args) {
        DaoActEspecImpl daoActEspecImpl = new DaoActEspecImpl();
        Object[] activo = daoActEspecImpl.actEspecGet2(1);
        System.out.println("ID: " + activo[0] + "||"
                + " Activo:" + activo[1]
                + "||" + " Activo Espec.:" + " " + activo[2]);
    }
}
