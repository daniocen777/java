/*
 Validador de activos específicos
 */
package web.validator;

import dao.DaoActEspec;
import dao.DaoActivo;
import dao.impl.DaoActEspecImpl;
import dao.impl.DaoActivoImpl;
import dto.ActEspec;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;
import parainfo.json.JSon;

/**
 *
 * @author DANIEL
 */
public class ActEspecValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    private final DaoActEspec daoActEspec;
    private final DaoActivo daoActivo;
    //String activoCapturado;

    public ActEspecValidator(HttpServletRequest request) {
        this.request = request;
        this.daoActEspec = new DaoActEspecImpl();
        this.daoActivo = new DaoActivoImpl();
    }

    // Método para listar los activos específicos
    // Listar 
    public StringBuilder actEspecQry() {
        StringBuilder result;
        List<Object[]> list = daoActEspec.actEspecQry();
        if (list == null) {
            result = jSon.forMsg(daoActEspec.getMessage());
        } else {
            String[] titulos = {"idactespec", "nombactivo", "nombactespec"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }

    // Método para llenar el combo de activos
    public StringBuilder activoCbo() {
        StringBuilder result;
        List<Object[]> list = daoActivo.activoCbo();

        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            result = jSon.forCbo(list);
        }

        return result;
    }

    // Método para insertar o actualizar
    public StringBuilder actEspecInsUpd(boolean upd) {
        List<String> message = new LinkedList<>();
        StringBuilder result = null;

        // Entradas
        Integer idactespec = DeString.toInteger(request.getParameter("idactespec"));
        Integer idactivo = DeString.toInteger(request.getParameter("idactivo"));
        String nombactespec = request.getParameter("nombactespec").toUpperCase();

        // Validaciones
        if (upd && (idactespec == null)) {
            message.add("ID requerido");
        }

        if (idactivo == null || idactivo == 0) {
            message.add("El activo es requerido");
        }

        if (nombactespec == null || nombactespec.trim().length() == 0) {
            message.add("Nombre del activo específico es requerido");
        } else if (nombactespec.length() <= 2 || nombactespec.length() > 50) {
            message.add("Nombre de activo [3; 50] caracteres");
        }

        // Encapsulamiento
        ActEspec actEspec = new ActEspec();
        actEspec.setIdactespec(idactespec);
        actEspec.setIdactivo(idactivo);
        actEspec.setNombactespec(nombactespec);

        if (message.isEmpty()) {
            //activoCapturado = daoActEspec.getActivoXid(idactespec);
            String msg = upd ? "No implmentado" : daoActEspec.actEspecIns(actEspec);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        } else {
            result = jSon.forMsg(message);
        }

        return result;
    }

    // Método para eliminar 
    public StringBuilder actEspecDel() {
        StringBuilder result = null;
        List<Integer> ids = DeString.ids(request.getParameter("ids"));
        if (ids == null) {
            result = jSon.forMsg("Lista de ID(s) incorrecta");
        } else {
            String msg = daoActEspec.actEspecDel(ids);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        }
        return result;
    }
}
