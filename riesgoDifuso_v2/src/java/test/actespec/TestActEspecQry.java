package test.actespec;

import dao.DaoActEspec;
import dao.impl.DaoActEspecImpl;
import java.util.List;

public class TestActEspecQry {
    
    public static void main(String[] args) {
        
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        List<Object[]> list = daoActEspec.actEspecQry();
        
        for (Object[] a : list) {
            System.out.println("ID: " + a[0].toString() 
                    + "Activo: " + a[1].toString()
                    + "Activo específico: " + a[2].toString());
        }
    }
}
