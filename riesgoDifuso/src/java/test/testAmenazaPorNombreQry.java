package test;

import dao.DaoAmenaza;
import dao.impl.DaoAmenazaImpl;
import java.util.List;

public class testAmenazaPorNombreQry {

    public static void main(String[] args) {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        List<Object[]> list = daoAmenaza.amenazaPorNombreQry("robo");

        if (list != null) {
            for (Object[] a : list) {
                System.out.println(a[0] + "||" + a[1]);
            }
        } else {
            System.out.println(daoAmenaza.getMessage() + "Error");
        }
    }
}
