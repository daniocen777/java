package web.validator;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;
import parainfo.json.JSon;

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
            String[] titulos = {"idactivo", "nombact", "tipoact"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }

    public StringBuilder activoIns(boolean upd) {
        List<String> message = new LinkedList<>();
        StringBuilder result = null;

        Integer idactivo = DeString.toInteger(request.getParameter("idactivo"));
        String nombact = request.getParameter("nombact");
        String tipoact = request.getParameter("tipoact");

        if (upd && (idactivo == null)) {
            message.add("ID requerido");
            //result = jSon.forMsg("ID requerido");
        }

        if (nombact == null || nombact.trim().length() == 0) {
            message.add("Nombre de activo es requerido");
        } else if (nombact.length() <= 2 || nombact.length() > 50) {
            message.add("Nombre de activo [3; 50] caracteres");
        }

        if (nombact.equalsIgnoreCase("Daniel")) {
            message.add("Nombre de activo no puede ser Daniel");
        }

        if (tipoact == null || tipoact.trim().length() == 0) {
            message.add("Tipo de activo es requerido");
        } else if (tipoact.length() <= 2 || tipoact.length() > 50) {
            message.add("Tipo de activo [3; 50] caracteres");
        }

        if (!(tipoact.equals("HARDWARE")) && !(tipoact.equals("SOFTWARE"))) {
            message.add("Tipo de activo sólo puede ser HARDWARE o SOFTWARE");
        }

        Activo activo = new Activo();
        activo.setIdactivo(idactivo);
        activo.setNombact(nombact);
        activo.setTipoact(tipoact);

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

    // Eliminar activo
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

    // GET
    public StringBuilder activoGet() {
        StringBuilder result = null;
        Integer idactivo = DeString.toInteger(request.getParameter("idactivo"));
        if (idactivo != null) {
            Activo activo = daoActivo.activoGet(idactivo);

            if (activo != null) {
                String[] til = {"idactivo", "nombact", "tipoact"};
                Object[] dat = {activo.getIdactivo(), activo.getNombact(), activo.getTipoact()};

                result = jSon.forUpd(til, dat);
            } else {
                result = jSon.forMsg(daoActivo.getMessage());
            }
        }
        return result;
    }

    // Buscar por activo por nombre
    public StringBuilder activoPorNombreQry() {
        StringBuilder result;
        String nombact = request.getParameter("nombact");
        List<Object[]> list = daoActivo.activoPorNombreQry(nombact);
        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            String[] titulos = {"idactivo", "nombact", "tipoact"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }

    // Cantidad de páginas
    public StringBuilder activosPags(Integer filsXpag) {
        Integer ctasPag = daoActivo.activosPags(filsXpag);
        return jSon.forMsg(ctasPag.toString());
        //return ctasPag != null ? ctasPag : -1;
    }

    // Activos paginados
    public StringBuilder activos(Integer filsXpag) {
        StringBuilder result;
        Integer numPag = DeString.toInteger(request.getParameter("numPag"));
        List<Object[]> list = daoActivo.activos(numPag, filsXpag);
        if (list == null) {
            result = jSon.forMsg(daoActivo.getMessage());
        } else {
            String[] titulos = {"idactivo", "nombact", "tipoact"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }
}
