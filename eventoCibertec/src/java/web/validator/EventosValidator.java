package web.validator;

import dao.DaoTipoEventos;
import dao.DaoEventos;
import dao.impl.DaoTipoEventosImpl;
import dao.impl.DaoEventosImpl;
import dto.Eventos;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;

public class EventosValidator {

    private final HttpServletRequest request;
    private final DaoEventos daoEventos;
    private final DaoTipoEventos daoTipoEventos;

    public EventosValidator(HttpServletRequest request) {
        this.request = request;
        this.daoEventos = new DaoEventosImpl();
        this.daoTipoEventos = new DaoTipoEventosImpl();
    }

    public List<String> eventosQry() {
        List<String> result = null;

        List<Object[]> list = daoEventos.eventosQry();
        if (list != null) {
            request.setAttribute("list", list);
        } else {
            result = new LinkedList<>();
            result.add(daoEventos.getMessage());
        }

        return result;
    }

    public List<String> eventosInsUpd(boolean upd) { // si upd==true es Update
        List<String> result = new LinkedList<>();

        // envios del cliente
        Integer idevento = DeString.toInteger(request.getParameter("idevento"));
        Integer idtipoEvento = DeString.toInteger(request.getParameter("idtipoEvento"));
        String evento = request.getParameter("evento");
        String descripcionevento = request.getParameter("descripcionevento");
        // Fechas
        String comienzo = request.getParameter("comienzo");
        String fin = request.getParameter("fin");

        String detalle = request.getParameter("detalle");

        // validaciones a los NOT NULL
        if (upd && idevento == null) {
            result.add("idevento requerido");
        }

        if (idtipoEvento == null) {
            result.add("idtipoEvento requerido");
        }

        if (evento == null || evento.trim().length() == 0) {
            result.add("Campo Evento requerido");
        } else if (evento.length() < 5 || evento.length() > 100) {
            result.add("evento [5, 100] caracteres");
        }
        if (descripcionevento == null || descripcionevento.trim().length() == 0) {
            result.add("Campo Descripción de evento requerido");
        } else if (descripcionevento.length() < 5 || descripcionevento.length() > 1000) {
            result.add("descripcionevento [5, 1000] caracteres");
        }

//        if ((DeString.toDate(comienzo)).compareTo(DeString.toDate(fin)) > 0) {
//            result.add("Error en colocar la fecha y hora");
//        }
        if ((comienzo).compareTo(fin) > 0) {
            result.add("Error en colocar la fecha y hora de comienzo y fin");
        }

        // encapsulamiento
        Eventos eventos = new Eventos();
        eventos.setIdevento(idevento);
        eventos.setIdtipoevento(idtipoEvento);
        eventos.setEvento(evento);
        eventos.setDescripcionevento(descripcionevento);
        eventos.setComienzo(comienzo);
        eventos.setFin(fin);
        eventos.setDetalle(detalle);

        // operacion dao
        if (result.isEmpty()) {
            String msg = upd
                    ? daoEventos.eventosUpd(eventos)
                    : daoEventos.eventosIns(eventos);

            if (msg != null) {
                result.add(msg);
            }
        }

        if (!result.isEmpty()) {
            request.setAttribute("eventos", eventos);
            combos();
        }

        // retorno
        return result.isEmpty() ? null : result;
    }

    public List<String> eventosDel() {
        List<String> result = new LinkedList<>();

        List<Integer> ids = DeString.ids(request.getParameter("ids"));

        if (ids != null) {
            String msg = daoEventos.eventosDel(ids);

            if (msg != null) {
                result.add(msg);
            }

        } else {
            result.add("IDs incorrectos");
        }

        return result.isEmpty() ? null : result;
    }

    public List<String> eventosGet() {
        List<String> result = new LinkedList<>();

        Integer idevento = DeString.toInteger(request.getParameter("idevento"));

        if (idevento != null) {
            Eventos eventos = daoEventos.eventosGet(idevento);

            if (eventos != null) {
                request.setAttribute("eventos", eventos);
            } else {
                result.add(daoEventos.getMessage());
            }

        } else {
            result.add("idevento requerido");
        }

        combos();
        return result.isEmpty() ? null : result;
    }

    public List<String> combos() {
        List<String> result = null;

        List<Object[]> list = daoTipoEventos.tipoEventosCbo();
        if (list != null) {
            request.setAttribute("list", list);
        } else {
            result = new LinkedList<>();
            result.add(daoTipoEventos.getMessage());
        }

        return result;
    }
}
