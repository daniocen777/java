package web.validator;

import dao.DaoAmenaza;
import dao.impl.DaoAmenazaImpl;
import dto.Amenaza;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import parainfo.convert.DeString;
import parainfo.json.JSon;

public class AmenazaValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    private final DaoAmenaza daoAmenaza;

    public AmenazaValidator(HttpServletRequest request) {
        this.request = request;
        this.daoAmenaza = new DaoAmenazaImpl();
    }

    public StringBuilder amenazaQry() {
        StringBuilder result;
        List<Object[]> list = daoAmenaza.amenazaQry();
        if (list == null) {
            result = jSon.forMsg(daoAmenaza.getMessage());
        } else {
            String[] titulos = {"idamenaza", "nombamen"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }

    // Insertar o actualizar Amenaza
    public StringBuilder amenazaInsUpd(boolean upd) {
        List<String> message = new LinkedList<>();
        StringBuilder result = null;

        Integer idamenaza = DeString.toInteger(request.getParameter("idamenaza"));
        String nombamen = request.getParameter("nombamen");

        if (upd && (idamenaza == null)) {
            message.add("ID requerido");
            //result = jSon.forMsg("ID requerido");
        }

        if (nombamen == null || nombamen.trim().length() == 0) {
            message.add("Nombre de amenaza es requerido");
        } else if (nombamen.length() <= 2 || nombamen.length() > 50) {
            message.add("Nombre de amenaza [3; 50] caracteres");
        }

        Amenaza amenaza = new Amenaza();
        amenaza.setIdamenaza(idamenaza);
        amenaza.setNombamen(nombamen);

        if (message.isEmpty()) {
            String msg = upd ? daoAmenaza.amenazaUpd(amenaza) : daoAmenaza.amenazaIns(amenaza);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        } else {
            result = jSon.forMsg(message);
        }

        return result;

    }

    // Eliminar activo
    public StringBuilder amenazaDel() {
        StringBuilder result = null;
        List<Integer> ids = DeString.ids(request.getParameter("ids"));
        if (ids == null) {
            result = jSon.forMsg("Lista de ID(s) incorrecta");
        } else {
            String msg = daoAmenaza.amenazaDel(ids);

            if (msg != null) {
                result = jSon.forMsg(msg);
            }
        }
        return result;
    }

    // GET
    public StringBuilder amenazaGet() {
        StringBuilder result = null;
        Integer idamenaza = DeString.toInteger(request.getParameter("idamenaza"));
        if (idamenaza != null) {
            Amenaza amenaza = daoAmenaza.amenazaGet(idamenaza);

            if (amenaza != null) {
                String[] til = {"idamenaza", "nombamen"};
                Object[] dat = {amenaza.getIdamenaza(), amenaza.getNombamen()};

                result = jSon.forUpd(til, dat);
            }
        }
        return result;
    }
    
     // Buscar amenaza por nombre
    public StringBuilder amenazaPorNombreQry() {
        StringBuilder result;
        String nombamen = request.getParameter("nombamen");
        List<Object[]> list = daoAmenaza.amenazaPorNombreQry(nombamen);
        if (list == null) {
            result = jSon.forMsg(daoAmenaza.getMessage());
        } else {
            String[] titulos = {"idamenaza", "nombamen"};
            result = jSon.forQry(titulos, list);
        }
        return result;
    }
}
