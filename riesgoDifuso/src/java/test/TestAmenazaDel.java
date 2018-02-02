package test;

import dao.DaoAmenaza;
import dao.impl.DaoAmenazaImpl;
import java.util.LinkedList;
import java.util.List;

public class TestAmenazaDel {

    public static void main(String[] args) {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        List<Integer> lista = new LinkedList<>();
        lista.add(3);
        lista.add(4);
        String message = daoAmenaza.amenazaDel(lista);

        System.out.println(message);
    }
}
