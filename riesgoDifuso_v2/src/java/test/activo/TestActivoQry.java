/*
 Pruebas para la clase Activo, DaoActivoImpl
 */
package test.activo;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public class TestActivoQry {

    public static void main(String[] args) {
        DaoActivo daoActivo = new DaoActivoImpl();
        List<Object[]> list = daoActivo.activoQry();

        if (list != null) {
            for (Object[] a : list) {
                System.out.println(a[0] + "||" + a[1] + "||" + a[2]);
            }
        } else {
            System.out.println(daoActivo.getMessage());
        }
    }

}
