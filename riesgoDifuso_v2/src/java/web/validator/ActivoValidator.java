/*
 Validador para la clase Activo ==> DaoActivo
 Clase que comunica con el servlet
 */
package web.validator;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;
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

    // Método para listar activos
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

    // Método para insertar o actualizar activos
    public StringBuilder activoInsUpd(boolean upd) {
        List<String> message = new LinkedList<>();
        StringBuilder result = null;

        Integer idactivo = DeString.toInteger(request.getParameter("idactivo"));
        String nombactivo = request.getParameter("nombactivo").toUpperCase();
        String tipo = request.getParameter("tipo");

        if (upd && (idactivo == null)) {
            message.add("ID requerido");
            //result = jSon.forMsg("ID requerido");
        }

        if (nombactivo == null || nombactivo.trim().length() == 0) {
            message.add("Nombre de activo es requerido");
        } else if (nombactivo.length() <= 2 || nombactivo.length() > 50) {
            message.add("Nombre de activo entre [3; 50] caracteres");
        }

        if (tipo == null || tipo.trim().length() == 0) {
            message.add("Tipo de activo es requerido");
        } else if (tipo.length() <= 2 || tipo.length() > 50) {
            message.add("Tipo de activo [3; 50] caracteres");
        }

        if (!(tipo.equals("HARDWARE")) && !(tipo.equals("SOFTWARE"))) {
            message.add("Tipo de activo sólo puede ser HARDWARE o SOFTWARE");
        }

        Activo activo = new Activo();
        activo.setIdactivo(idactivo);
        activo.setNombactivo(nombactivo);
        activo.setTipo(tipo);

        if (message.isEmpty()) {
            String msg = upd ? daoActivo.activoUpd(activo) : daoActivo.activoIns(activo);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        } else {
            result = jSon.forMsg(message);
        }

        return result;
    }

    // Método para eliminar activo
    public StringBuilder activoDel() {
        StringBuilder result = null;
        List<Integer> ids = DeString.ids(request.getParameter("ids"));
        if (ids == null) {
            result = jSon.forMsg("Lista de ID(s) incorrecta");
        } else {
            String msg = daoActivo.activoDel(ids);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        }
        return result;
    }

    // Método para obtener un activo
    // GET
    public StringBuilder activoGet() {
        StringBuilder result = null;
        Integer idactivo = DeString.toInteger(request.getParameter("idactivo"));
        if (idactivo != null) {
            Activo activo = daoActivo.activoGet(idactivo);

            if (activo != null) {
                String[] til = {"idactivo", "nombactivo", "tipo"};
                Object[] dat = {activo.getIdactivo(), activo.getNombactivo(), activo.getTipo()};

                result = jSon.forUpd(til, dat);
            } else {
                result = jSon.forMsg(daoActivo.getMessage());
            }
        }
        return result;
    }

    // Buscar activo por nombre
    public StringBuilder activoPorNombreQry() {
        StringBuilder result;
        String nombactivo = request.getParameter("nombactivo");
        List<Object[]> list = daoActivo.activoPorNombreQry(nombactivo);
        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            String[] titulos = {"idactivo", "nombactivo", "tipo"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }
}
