package test;

import dao.DaoActEspec;
import dao.impl.DaoActEspecImpl;
import java.util.List;

public class TestActEspecQry {

    public static void main(String[] args) {
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        List<Object[]> list = daoActEspec.actEspecQry();

        if (list != null) {
            for (Object[] a : list) {
                System.out.println("ID: " + a[0] + " || " + " Activo: " + a[1] + " || " + " Act. Esp.: " + a[2]);
            }
        } else {
            System.out.println(daoActEspec.getMessage());
        }
    }
}
