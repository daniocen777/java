package web.validator;

import dto.Responsable;
import java.util.LinkedList;
import java.util.List;
import parainfo.convert.DeString;

public class ResponsableValidator {

    public List<String> responsableInsValidator(Responsable responsable) {
        List<String> list = new LinkedList<>();
        boolean isNum = false; // Validar celular
        boolean isNumDni = false; // Validar DNI

        // Validando DNI
        if (DeString.isNumeric(responsable.getDni())) {
            isNumDni = true;
        }
        if (!isNumDni) {
            list.add("DNI inválido. Sólo se permiten números");
        }
        if (responsable.getDni().length() != 8) {
            list.add("DNI debe contener 8 dígitos");
        }

        // Validando número de celular
        if (DeString.isNumeric(responsable.getCelular())) {
            isNum = true;
        }
        if (!isNum) {
            list.add("Número de celular inválido");
        }
        if (responsable.getCelular().length() != 9) {
            list.add("Número de celular debe contener 9 dígitos");
        }

        return list;
    }
}
