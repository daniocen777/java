package test;

import dao.DaoAmenaza;
import dao.impl.DaoAmenazaImpl;
import dto.Amenaza;

public class TestAmenazaGet {

    public static void main(String[] args) {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        Amenaza amenaza = daoAmenaza.amenazaGet(3);
        System.out.println("ID: " + amenaza.getIdamenaza() + " || " +  "Nombre de amenaza: " + amenaza.getNombamen());

    }
}
