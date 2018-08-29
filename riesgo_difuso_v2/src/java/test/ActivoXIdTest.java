package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;

public class ActivoXIdTest {

    public static void main(String[] args) {

        DaoActivo daoActivo = new DaoActivoImpl();
        String activo = daoActivo.activoXId(15);
        
        System.out.println("Activo: " + activo);
    }
}
