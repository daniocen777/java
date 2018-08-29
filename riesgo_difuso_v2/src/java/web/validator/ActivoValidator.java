package web.validator;

import dto.Activo;
import java.util.LinkedList;
import java.util.List;
import parainfo.convert.DeString;

public class ActivoValidator {

    public List<String> activoInsValidator(Activo activo) {
        List<String> list = new LinkedList<>();
        boolean isNum = false;

        if (DeString.isNumeric(activo.getNombactivo())) {
            isNum = true;
        }

        if (isNum) {
            list.add("El activo no puede comenzar con números");
        }

        if (!(activo.getTipo().equals("HARDWARE")) && !(activo.getTipo().equals("SOFTWARE"))) {
            list.add("Tipo de activo sólo puede ser HARDWARE o SOFTWARE");
        }

        return list;
    }
}
