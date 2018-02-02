package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;

public class TestActivoIns {

    public static void main(String[] args) {
        DaoActivo daoActivo = new DaoActivoImpl();
        Activo activo = new Activo();
        // Setter datos
        activo.setNombact("ACTIVO 3");
        activo.setTipoact("TIPO ACTIVO 3");

        String message = daoActivo.activoIns(activo);

        System.out.println(message);
    }
}
