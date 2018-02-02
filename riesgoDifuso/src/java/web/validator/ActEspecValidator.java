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

public class ActEspecValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    private final DaoActEspec daoActEspec;
    private final DaoActivo daoActivo;

    public ActEspecValidator(HttpServletRequest request) {
        this.request = request;
        this.daoActEspec = new DaoActEspecImpl();
        this.daoActivo = new DaoActivoImpl();
    }

    // Listar 
    public StringBuilder actEspecQry() {
        StringBuilder result;
        List<Object[]> list = daoActEspec.actEspecQry();
        if (list == null) {
            result = jSon.forMsg(daoActEspec.getMessage());
        } else {
            String[] titulos = {"idactespec", "nombact", "nombactespec"};
            result = jSon.forQry(titulos, list);
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
        String nombactespec = request.getParameter("nombactespec");

        // Validaciones
        if (upd && (idactespec == null)) {
            message.add("ID requerido");
            //result = jSon.forMsg("ID requerido");
        }

        if (idactivo == null) {
            message.add("idactivo requerido");
        }

        if (nombactespec == null || nombactespec.trim().length() == 0) {
            message.add("Nombre de activo es requerido");
        } else if (nombactespec.length() <= 2 || nombactespec.length() > 50) {
            message.add("Nombre de activo [3; 50] caracteres");
        }

        // Encapsulamiento
        ActEspec actEspec = new ActEspec();
        actEspec.setIdactespec(idactespec);
        actEspec.setIdactivo(idactivo);
        actEspec.setNombactespec(nombactespec);

        if (message.isEmpty()) {
            String msg = upd ? daoActEspec.actEspecUpd(actEspec) : daoActEspec.actEspecIns(actEspec);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        } else {
            result = jSon.forMsg(message);
        }

        return result;
    }

    // Método para poblar los combos
    public StringBuilder combos() {
        StringBuilder result;
        List<Object[]> list = daoActivo.activoCbo();

        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            result = jSon.forCbo(list);
        }

        return result;
    }

    // GET
    public StringBuilder actEspecGet() {
        StringBuilder result = null;
        Integer idactespec = DeString.toInteger(request.getParameter("idactespec"));
        if (idactespec != null) {
            ActEspec actEspec = daoActEspec.actEspecGet(idactespec);

            if (actEspec != null) {
                String[] titulos = {"idactespec", "idactivo", "nombactespec"};
                Object[] dat = {actEspec.getIdactespec(), actEspec.getIdactivo(), actEspec.getNombactespec()};

                result = jSon.forUpd(titulos, dat);
            } else {
                result = jSon.forMsg(daoActEspec.getMessage());
            }
        }
        combos();
        return result;
    }

    // GET
    public StringBuilder actEspecGet2() {
        StringBuilder result = null;
        Integer idactespec = DeString.toInteger(request.getParameter("idactespec"));
        if (idactespec != null) {
            Object[] actEspec = daoActEspec.actEspecGet2(idactespec);

            if (actEspec != null) {
                String[] titulos = {"idactespec", "nombact", "nombactespec"};
                Object[] dat = {actEspec[0], actEspec[1], actEspec[2]};

                result = jSon.forUpd(titulos, dat);
            } else {
                result = jSon.forMsg(daoActEspec.getMessage());
            }
        }
        combos();
        return result;
    }

    // Eleminar activos específicos
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

    // Buscar por activo por nombre
    public StringBuilder activoEspecPorNombreQry() {
        StringBuilder result;
        String nombactespec = request.getParameter("nombactespec");
        List<Object[]> list = daoActEspec.activoEspecPorNombreQry(nombactespec);
        if (list == null) {
            result = jSon.forMsg(daoActEspec.getMessage());
        } else {
            String[] titulos = {"idactespec", "nombact", "nombactespec"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }
}
