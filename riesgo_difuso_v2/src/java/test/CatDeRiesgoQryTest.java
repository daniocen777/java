package test;

import dao.DaoCatDeRiesgo;
import dao.impl.DaoCatDeRiesgoImpl;
import dto.CatDeRiesgo;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import parainfo.convert.DeDate;
import parainfo.convert.DeString;

public class CatDeRiesgoQryTest {

    public static void main(String[] args) {

//        DaoCatDeRiesgo daoCatDeRiesgo = new DaoCatDeRiesgoImpl();
//        
//        List<CatDeRiesgo> list = daoCatDeRiesgo.catDeRiesgoQry();
//        
//        for (CatDeRiesgo cat : list) {
//            System.out.println(cat.getCategoria());
//        }
        LocalDate today = LocalDate.now();
        Integer ano = today.getYear();
        Integer mes = today.getMonthValue();
        Integer dia = today.getDayOfMonth();
        String fecha = dia + "-" + mes + "-" + ano;
        Date laFecha = DeString.aDate(fecha);

        System.out.println(laFecha);
    }
}
