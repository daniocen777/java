package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import java.util.LinkedList;
import java.util.List;

public class TestActivoDel {

    public static void main(String[] args) {
        DaoActivo daoActivo = new DaoActivoImpl();
        List<Integer> lista = new LinkedList<>();
        //lista.add(8);
        lista.add(10);
        String mensaje = daoActivo.activoDel(lista);

        System.out.println(mensaje);
    }
}
