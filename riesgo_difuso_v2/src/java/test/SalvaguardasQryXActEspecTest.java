package test;

import dao.DaoSalvaguarda;
import dao.impl.DaoSalvaguardaImpl;
import dto.Salvaguarda;
import java.util.List;

public class SalvaguardasQryXActEspecTest {
    
    public static void main(String[] args) {
        
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        
        List<Salvaguarda> list = daoSalvaguarda.salvaguardasQryXActEspec(14, 27);
        
        for (Salvaguarda salv : list) {
            System.out.println("Amenaza: " + salv.getAmenaza().getAmenaza());
        }
    }
}
