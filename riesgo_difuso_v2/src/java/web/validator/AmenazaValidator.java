package web.validator;

import dto.Amenaza;
import java.util.LinkedList;
import java.util.List;

public class AmenazaValidator {

    public List<String> amenazaValidatorIns(Amenaza amenaza) {
        List<String> list = new LinkedList<>();

        if (!(amenaza.getCatDeRiesgo().getIdcatderiesgo() == 1)
                && !(amenaza.getCatDeRiesgo().getIdcatderiesgo() == 2)
                && !(amenaza.getCatDeRiesgo().getIdcatderiesgo() == 3)
                && !(amenaza.getCatDeRiesgo().getIdcatderiesgo() == 4)
                && !(amenaza.getCatDeRiesgo().getIdcatderiesgo() == 5)) {

            list.add("Categoría no especificada");
        }
        return list;
    }
}
