package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;

public class TestActivoGet {

    public static void main(String[] args) {
        DaoActivo daoActivo = new DaoActivoImpl();
        Activo activo = daoActivo.activoGet(5);
        System.out.println("ID: " + activo.getIdactivo() + "||"
                + " Nombre:" + activo.getNombact()
                + "||" + " Tipo de activo:" + " " + activo.getTipoact());
    }
}
