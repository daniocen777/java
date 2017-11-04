package web.validator;

import dao.DaoTipoEventos;
import dao.impl.DaoTipoEventosImpl;
import dto.TipoEventos;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;

public class TipoEventosValidator {

    private final HttpServletRequest request;
    private final DaoTipoEventos daoTipoEventos;

    public TipoEventosValidator(HttpServletRequest request) {
        this.request = request;
        this.daoTipoEventos = new DaoTipoEventosImpl();
    }

    public List<String> tipoEventosQry() {
        List<String> result = null;

        List<TipoEventos> list = daoTipoEventos.tipoEventosQry();
        if (list != null) {
            request.setAttribute("list", list);
        } else {
            result = new LinkedList<>();
            result.add(daoTipoEventos.getMessage());
        }

        return result;
    }

    public List<String> tipoEventosInsUpd(boolean upd) { // si upd==true es Update
        List<String> result = new LinkedList<>();

        // envios del cliente
        Integer idtipoevento = DeString.toInteger(request.getParameter("idtipoevento"));
        String tipoevento = request.getParameter("tipoevento");
        String descripciontipo = request.getParameter("descripciontipo");

        // validaciones a los NOT NULL
        if (upd && idtipoevento == null) {
            result.add("idtipoEvento requerido");
        }

        if (tipoevento == null || tipoevento.trim().length() == 0) {
            result.add("Tipo de evento requerido");
        } else if (tipoevento.length() < 5 || tipoevento.length() > 100) {
            result.add("Tipo de evento entre [5, 100] caracteres");
        }

        // encapsulamiento
        TipoEventos tipoEventos = new TipoEventos();
        tipoEventos.setIdtipoevento(idtipoevento);
        tipoEventos.setTipoevento(tipoevento);
        tipoEventos.setDescripciontipo(descripciontipo);

        // operacion dao
        if (result.isEmpty()) {
            String msg = upd
                    ? daoTipoEventos.tipoEventosUpd(tipoEventos)
                    : daoTipoEventos.tipoEventosIns(tipoEventos);

            if (msg != null) {
                result.add(msg);
            }
        } else {
            request.setAttribute("tipoEventos", tipoEventos);
        }

        // retorno
        return result.isEmpty() ? null : result;
    }

    public List<String> tipoEventosDel() {
        List<String> result = new LinkedList<>();

        List<Integer> ids = DeString.ids(request.getParameter("ids"));

        if (ids != null) {
            String msg = daoTipoEventos.tipoEventosDel(ids);

            if (msg != null) {
                result.add(msg);
            }

        } else {
            result.add("IDs incorrectos");
        }

        return result.isEmpty() ? null : result;
    }

    public List<String> tipoEventosGet() {
        List<String> result = new LinkedList<>();

        Integer idtipoEvento = DeString.toInteger(request.getParameter("idtipoEvento"));

        if (idtipoEvento != null) {
            TipoEventos tipoEventos = daoTipoEventos.tipoEventosGet(idtipoEvento);

            if (tipoEventos != null) {
                request.setAttribute("tipoEventos", tipoEventos);
            } else {
                result.add(daoTipoEventos.getMessage());
            }

        } else {
            result.add("idtipoEvento requerido");
        }

        return result.isEmpty() ? null : result;
    }
}
