package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import java.util.List;

public class TestActivoPorNombreQry {

    public static void main(String[] args) {

        DaoActivo daoActivo = new DaoActivoImpl();
        List<Object[]> list = daoActivo.activoPorNombreQry("p");

        if (list != null) {
            for (Object[] a : list) {
                System.out.println(a[0] + "||" + a[1] + "||" + a[2]);
            }
        } else {
            System.out.println(daoActivo.getMessage() + "Error");
        }
    }
}
