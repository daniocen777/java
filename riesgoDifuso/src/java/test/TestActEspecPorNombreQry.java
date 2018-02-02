package test;

import dao.DaoActEspec;
import dao.impl.DaoActEspecImpl;
import java.util.List;

public class TestActEspecPorNombreQry {

    public static void main(String[] args) {
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        List<Object[]> list = daoActEspec.activoEspecPorNombreQry("cpu");

        if (list != null) {
            for (Object[] a : list) {
                System.out.println(a[0] + "||" + a[1] + "||" + a[2]);
            }
        } else {
            System.out.println(daoActEspec.getMessage() + "Error");
        }
    }
}
