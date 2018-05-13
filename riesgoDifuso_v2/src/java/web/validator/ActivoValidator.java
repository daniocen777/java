/*
 Validador para la clase Activo ==> DaoActivo
 */
package web.validator;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.json.JSon;

/**
 *
 * @author DANIEL
 */
public class ActivoValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    private final DaoActivo daoActivo;

    public ActivoValidator(HttpServletRequest request) {
        this.request = request;
        this.daoActivo = new DaoActivoImpl();
    }

    public StringBuilder activoQry() {
        StringBuilder result;
        List<Object[]> list = daoActivo.activoQry();
        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            String[] titulos = {"idactivo", "nombactivo", "tipo"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }
}
