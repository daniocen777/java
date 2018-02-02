package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;

public class TestActivoUpd {

    public static void main(String[] args) {
        DaoActivo daoActivo = new DaoActivoImpl();
        Activo activo = new Activo();
        activo.setNombact("APLICACIONES");
        activo.setTipoact("SOFTWARE APP");
        activo.setIdactivo(5);
        String message = daoActivo.activoUpd(activo);

        System.out.println(message);

    }
}
