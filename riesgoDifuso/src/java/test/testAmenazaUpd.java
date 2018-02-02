package test;

import dao.DaoAmenaza;
import dao.impl.DaoAmenazaImpl;
import dto.Amenaza;

public class testAmenazaUpd {

    public static void main(String[] args) {
        DaoAmenaza daoAmenaza = new DaoAmenazaImpl();
        Amenaza amenaza = new Amenaza();
        amenaza.setNombamen("Amenaza 3 Actualizada");
        amenaza.setIdamenaza(3);
        String message = daoAmenaza.amenazaUpd(amenaza);
        System.out.println(message);
    }
}
