package test;

import dao.DaoAmenaza;
import dao.impl.DaoAmenazaImpl;
import dto.Amenaza;

public class TestAmenazaIns {

    public static void main(String[] args) {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        Amenaza amenaza = new Amenaza();
        amenaza.setNombamen("AMENAZA 4");
        String message = daoAmenaza.amenazaIns(amenaza);
        System.out.println(message);
    }
}
