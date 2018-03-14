package web.validator;

import dao.DaoActEspecAmen;
import dao.DaoCuantRiesgo;
import dao.impl.DaoActEspecAmenImpl;
import dao.impl.DaoCuantRiesgoImpl;
import dto.CuantRiesgo;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;
import parainfo.json.JSon;

public class CuantRiesgoValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    private final DaoActEspecAmen actEspecAmen;
    private final DaoCuantRiesgo daoCuantRiesgo;

    public CuantRiesgoValidator(HttpServletRequest request) {
        this.request = request;
        this.actEspecAmen = new DaoActEspecAmenImpl();
        this.daoCuantRiesgo = new DaoCuantRiesgoImpl();
    }

    // Listar
    public StringBuilder cuantRiesgoQry() {
        StringBuilder result;
        List<Object[]> list = daoCuantRiesgo.cuantRiesgoQry();
        if (list == null) {
            result = jSon.forMsg(daoCuantRiesgo.getMessage());
        } else {
            String[] titulos = {"idcuantriesgo", "nombact", "nombactespec",
                "nombamen", "amenaza", "vulnerabilidad",
                "impacto", "defu", "riesgo"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }

    // Insertar
    public StringBuilder cuantRiesgoInsUpd(boolean upd) {
        List<String> message = new LinkedList<>();
        StringBuilder result = null;

        // Entradas
        Integer idcuantriesgo = DeString.toInteger(request.getParameter("idcuantriesgo"));
        Integer idactespecamen = DeString.toInteger(request.getParameter("idactespecamen"));
        String amenaza = request.getParameter("amenaza");
        String vulnerabilidad = request.getParameter("vulnerabilidad");
        String impacto = request.getParameter("impacto");
        // Validaciones
        if (upd && (idcuantriesgo == null)) {
            message.add("ID requerido");
            //result = jSon.forMsg("ID requerido");
        }

        if (idactespecamen == null) {
            message.add("idactespecamen requerido");
        }

        if (amenaza == null || amenaza.trim().length() == 0) {
            message.add("La amenaza es requerida");
        } else if ((!amenaza.equals("MB")) && (!amenaza.equals("B"))
                && (!amenaza.equals("M")) && (!amenaza.equals("A"))
                && (!amenaza.equals("MA"))) {
            message.add("Amenaza no especificada");
        }

        if (vulnerabilidad == null || vulnerabilidad.trim().length() == 0) {
            message.add("La vulnerabilidad es requerida");
        } else if ((!vulnerabilidad.equals("MB")) && (!vulnerabilidad.equals("B"))
                && (!vulnerabilidad.equals("M")) && (!vulnerabilidad.equals("A"))
                && (!vulnerabilidad.equals("MA"))) {
            message.add("Vulnerabilidad no especificada");
        }

        if (impacto == null || impacto.trim().length() == 0) {
            message.add("El impacto es requerido");
        } else if ((!impacto.equals("MB")) && (!impacto.equals("B"))
                && (!impacto.equals("M")) && (!impacto.equals("A"))
                && (!impacto.equals("MA"))) {
            message.add("Impacto no especificado");
        }

        CuantRiesgo cuantRiesgo = new CuantRiesgo();
        cuantRiesgo.setIdcuantriesgo(idcuantriesgo);
        cuantRiesgo.setIdactespecamen(idactespecamen);
        cuantRiesgo.setAmenaza(amenaza);
        cuantRiesgo.setVulnerabilidad(vulnerabilidad);
        cuantRiesgo.setImpacto(impacto);

        if (message.isEmpty()) {
            String msg = upd ? "No hay aún" : daoCuantRiesgo.cuantRiesgoIns(cuantRiesgo);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        } else {
            result = jSon.forMsg(message);
        }

        return result;

    }

    // Método para llenar el combo de activos
    public StringBuilder actEspecAmenCbo() {
        StringBuilder result;
        List<Object[]> list = actEspecAmen.actEspecAmenCbo();

        if (list == null) {
            result = jSon.forMsg(actEspecAmen.getMessage());
        } else {
            String[] titulos = {"idactespecamen", "nombact", "nombactespec", "nombamen"};
            result = jSon.forQry(titulos, list);
        }

        return result;
    }
}
