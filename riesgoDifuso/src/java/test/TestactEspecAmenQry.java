package test;

import dao.DaoActEspecAmen;
import dao.impl.DaoActEspecAmenImpl;
import java.util.List;

public class TestactEspecAmenQry {

    public static void main(String[] args) {
        DaoActEspecAmen daoActEspecAmen = new DaoActEspecAmenImpl();
        List<Object[]> list = daoActEspecAmen.actEspecAmenQry();

        if (list != null) {
            for (Object[] a : list) {
                System.out.println("ID: " + a[0] + " || " + " Activo: " + a[1] + " || " + " Act. Esp.: " + a[2] + " || " + "Amenaza: " + a[3]);
            }
        } else {
            System.out.println(daoActEspecAmen.getMessage());
        }
    }
}
