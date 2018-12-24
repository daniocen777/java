package util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

@FacesConverter("valor")
public class Converter implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        String valor;
        valor = (String) o;
        switch (valor) {
            case "MB":
                valor = "MUY BAJO";
                break;
            case "B":
                valor = "BAJO";
                break;
            case "M":
                valor = "MEDIO";
                break;
            case "A":
                valor = "ALTO";
                break;

            case "MA":
                valor = "MUY ALTO";
                break;
            default:
                valor = "NO ESPECIFICADO";
        }

        return valor;
    }

}
