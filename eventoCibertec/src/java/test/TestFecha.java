package test;

import dao.DaoEventos;
import dao.impl.DaoEventosImpl;
import dto.Eventos;
import parainfo.convert.DeString;

public class TestFecha {

    public static void main(String[] args) {
//        String dateInString1 = "07/06/2014 12:30 p.m";
//        String dateInStrin2 = "07/06/2014 12:20 p.m";
        DaoEventos daoEventos = new DaoEventosImpl();
        Eventos eventos = daoEventos.eventosGet(2);

        if ((DeString.toDate(eventos.getComienzo())).compareTo(DeString.toDate(eventos.getFin())) > 0) {
            System.out.println("Error en colocar la fecha y hora");
        } else {
            System.out.println("Correcto");
        }
        System.out.println("Comienzo: " + eventos.getComienzo() + "final: " + eventos.getFin());
    }
}
