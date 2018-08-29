package web.validator;

import dto.Salvaguarda;
import java.util.LinkedList;
import java.util.List;

public class SalvaguardaValidator {

    public List<String> salvaguardaInsValidator(Salvaguarda salvaguarda) {
        List<String> list = new LinkedList<>();

        if (salvaguarda.getActEspec().getIdactespec() == null) {
            list.add("Ingese Activo Específico");
        }

        if (salvaguarda.getAmenaza().getIdamenaza() == null) {
            list.add("Ingese Amenaza");
        }

        return list;
    }
}
