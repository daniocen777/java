package test;

import dao.DaoActEspec;
import dao.impl.DaoActEspecImpl;
import dto.ActEspec;
import dto.Activo;
import java.util.LinkedList;
import java.util.List;

public class ActEspecsInsMultTest {

    public static void main(String[] args) {

        List<ActEspec> list = new LinkedList<>();
        ActEspec actEspec = new ActEspec();
        Activo activo = new Activo();
        activo.setIdactivo(14);
        actEspec.setActivo(activo);
        actEspec.setNombactespec("NOMBRE DE ACTIVO ESPECÍFICO DE PRUEBA PARA LISTA #1");

        ActEspec actEspec2 = new ActEspec();
        Activo activo2 = new Activo();
        activo2.setIdactivo(15);
        actEspec2.setActivo(activo);
        actEspec2.setNombactespec("NOMBRE DE ACTIVO ESPECÍFICO DE PRUEBA PARA LISTA #2");

        ActEspec actEspec3 = new ActEspec();
        Activo activo3 = new Activo();
        activo3.setIdactivo(16);
        actEspec3.setActivo(activo);
        actEspec3.setNombactespec("NOMBRE DE ACTIVO ESPECÍFICO DE PRUEBA PARA LISTA #3");

        list.add(actEspec);
        list.add(actEspec2);
        list.add(actEspec3);

        DaoActEspec daoActEspec = new DaoActEspecImpl();
        String message = daoActEspec.actEspecsInsMult(list);
        
        if (message == null) {
            System.out.println("¡CORRECTO!");
        } else {
            System.out.println("¡INCORRECTO!");
        }
    }
}
