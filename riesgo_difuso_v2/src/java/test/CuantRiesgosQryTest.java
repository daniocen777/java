package test;

import dao.DaoCuantRiesgo;
import dao.impl.DaoCuantRiesgoImpl;
import dto.CuantRiesgo;
import java.util.List;

public class CuantRiesgosQryTest {
    
    public static void main(String[] args) {
        DaoCuantRiesgo daoCuantRiesgo = new DaoCuantRiesgoImpl();
        
        List<CuantRiesgo> list = daoCuantRiesgo.cuantRiesgosQry();
        
        for (CuantRiesgo cuant : list) {
            System.out.println("Activo: " + cuant.getSalvaguarda_idsalvaguarda().getActEspec().getNombactespec()
            + " -- " + "Amenaza: " + cuant.getSalvaguarda_idsalvaguarda().getAmenaza().getAmenaza()
            + " -- " + "Defuzi: " + cuant.getDefu()
            + " -- " + "Riesgo: " + cuant.getRie());
        }
    }
}
