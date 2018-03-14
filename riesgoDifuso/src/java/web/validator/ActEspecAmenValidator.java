package web.validator;

import dao.DaoActEspec;
import dao.DaoActEspecAmen;
import dao.DaoActivo;
import dao.DaoAmenaza;
import dao.impl.DaoActEspecAmenImpl;
import dao.impl.DaoActEspecImpl;
import dao.impl.DaoActivoImpl;
import dao.impl.DaoAmenazaImpl;
import dto.ActEspecAmen;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;
import parainfo.json.JSon;

public class ActEspecAmenValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    private final DaoActEspecAmen daoActEspecAmen;
    private final DaoActivo daoActivo; // Para el combo de Activos
    private final DaoAmenaza daoAmenaza; // Para el combo de Activos
    private final DaoActEspec daoActEspec; // Para el combo de Activo Específicos

    public ActEspecAmenValidator(HttpServletRequest request) {
        this.request = request;
        this.daoActEspecAmen = new DaoActEspecAmenImpl();
        this.daoActivo = new DaoActivoImpl();
        this.daoAmenaza = new DaoAmenazaImpl();
        this.daoActEspec = new DaoActEspecImpl();
    }

    // Listar
    public StringBuilder actEspecAmenQry() {
        StringBuilder result;
        List<Object[]> list = daoActEspecAmen.actEspecAmenQry();
        if (list == null) {
            result = jSon.forMsg(daoActEspecAmen.getMessage());
        } else {
            String[] titulos = {"idactespecamen", "nombact", "nombactespec", "nombamen"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }

    // LLenar Combo de amenazas
    public StringBuilder amenazaCbo() {
        StringBuilder result;
        List<Object[]> list = daoAmenaza.amenazaCbo();

        if (list == null) {
            result = jSon.forMsg(daoAmenaza.getMessage());
        } else {
            result = jSon.forCbo(list);
        }

        return result;
    }

    // LLenar combos de activos
    public StringBuilder activosCbo() {
        StringBuilder result;
        List<Object[]> list = daoActivo.activoCbo();

        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            result = jSon.forCbo(list);
        }

        return result;
    }

    // Proceso para llenar el combo de activo específico
    public StringBuilder activoEspecCbo() {
        StringBuilder result;
        List<Object[]> list = daoActEspec.activoEspecCbo();

        if (list == null) {
            result = jSon.forMsg(daoActEspec.getMessage());
        } else {
            String[] titulos = {"idactivo", "idactespec", "nombactespec"};
            result = jSon.forQry(titulos, list);
        }

        return result;
    }

    // Proceso para insertar o actualizar
    public StringBuilder actEspecAmenIns(boolean upd) {
        List<String> message = new LinkedList<>();
        StringBuilder result = null;

        // Entradas
        Integer idactespecamen = DeString.toInteger(request.getParameter("idactespecamen"));
        Integer idactespec = DeString.toInteger(request.getParameter("idactespec"));
        Integer idamenaza = DeString.toInteger(request.getParameter("idamenaza"));

        // Validaciones
        if (upd && (idactespecamen == null)) {
            message.add("ID requerido");
            //result = jSon.forMsg("ID requerido");
        }

        if (idactespec == null) {
            message.add("idactespec requerido");
        }

        if (idamenaza == null) {
            message.add("idamenaza requerido");
        }

        // Encapsulamiento
        ActEspecAmen actEspecAmen = new ActEspecAmen();
        actEspecAmen.setIdactespecamen(idactespecamen);
        actEspecAmen.setIdactespec(idactespec);
        actEspecAmen.setIdamenaza(idamenaza);

        if (message.isEmpty()) {
            String msg = upd ? "daoActEspec.actEspecUpd(actEspec)" : daoActEspecAmen.actEspecAmenIns(actEspecAmen);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        } else {
            result = jSon.forMsg(message);
        }

        return result;
    }

    // Proceso para eliminar 
    public StringBuilder actEspecAmenDel() {
        StringBuilder result = null;
        List<Integer> ids = DeString.ids(request.getParameter("ids"));
        if (ids == null) {
            result = jSon.forMsg("Lista de ID(s) incorrecta");
        } else {
            String msg = daoActEspecAmen.actEspecAmenDel(ids);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        }
        return result;
    }
}
