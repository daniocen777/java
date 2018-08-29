package test;

import dao.DaoSalvaguarda;
import dao.impl.DaoSalvaguardaImpl;
import dto.Salvaguarda;
import java.util.List;

public class SalvaguardasQryTest {

    public static void main(String[] args) {

        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();

        List<Salvaguarda> list = daoSalvaguarda.salvaguardasQry();

        for (Salvaguarda salv : list) {
            System.out.println("ID: " + salv.getIdsalvaguarda()
                    + " Específico: " + salv.getActEspec().getNombactespec()
                    + " Amenaza: " + salv.getAmenaza().getAmenaza()
                    + " Responsable: " + salv.getResponsable().getNombre()
                    + " Control: " + salv.getControl());
        }
    }
}
