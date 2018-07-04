package test;

import dao.DaoAutores;
import dao.impl.DaoAutoresImpl;
import java.util.List;

public class TestAutoresQry {

    public static void main(String[] args) {

        DaoAutores daoAutores = new DaoAutoresImpl();

        List<Object[]> list = daoAutores.autoresQry();

        if (list != null) {
            for (Object[] a : list) {
                System.out.println("ID: " + a[0] + " || " + " Autor: " + a[1]);
            }
        } else {
            System.out.println(daoAutores.getMessage());
        }
    }
}
