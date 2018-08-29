package test;

import dao.DaoSalvaguarda;
import dao.impl.DaoSalvaguardaImpl;
import dto.ActEspec;
import dto.Amenaza;
import dto.Responsable;
import dto.Salvaguarda;

public class SalvaguardasUpdTest {
    
    public static void main(String[] args) {
        
        ActEspec actEspec = new ActEspec();
        actEspec.setIdactespec(30);
        Amenaza amenaza = new Amenaza();
        amenaza.setIdamenaza(9);
        Responsable resp = new Responsable();
        resp.setIdresponsable(2);
        
        Salvaguarda salv = new Salvaguarda();
        salv.setActEspec(actEspec);
        salv.setAmenaza(amenaza);
        salv.setResponsable(resp);
        salv.setIdsalvaguarda(70);
        salv.setControl("Control xxxxx");
        
        DaoSalvaguarda daoSalvaguarda = new DaoSalvaguardaImpl();
        String msg = daoSalvaguarda.salvaguardasUpd(salv);
        
        if (msg == null) {
            System.out.println("Editado correctamente");
        } else {
            System.out.println("ERRORRRR");
        }
    }
}
