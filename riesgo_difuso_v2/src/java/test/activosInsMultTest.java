package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;
import java.util.LinkedList;
import java.util.List;

public class activosInsMultTest {

    public static void main(String[] args) {

        Activo activo1 = new Activo();
        activo1.setNombactivo("Activo múltiple 1");
        activo1.setTipo("Tipo de Activo múltiple 1");

        Activo activo2 = new Activo();
        activo2.setNombactivo("Activo múltiple 2");
        activo2.setTipo("Tipo de Activo múltiple 2");

        Activo activo3 = new Activo();
        activo3.setNombactivo("Activo múltiple 3");
        activo3.setTipo("Tipo de Activo múltiple 3");

        List<Activo> listActivos = new LinkedList<>();
        listActivos.add(activo1);
        listActivos.add(activo2);
        listActivos.add(activo3);

        // Dao
        DaoActivo daoActivo = new DaoActivoImpl();
        String message = daoActivo.activosInsMult(listActivos);

        if (message == null) {
            System.out.println("Guardado");
        } else {
            System.out.println("ERRORRRR");
        }

    }
}
