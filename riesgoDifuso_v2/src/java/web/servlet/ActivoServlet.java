package web.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import parainfo.json.JSon;
import web.validator.ActivoValidator;

/**
 *
 * @author DANIEL
 */
@WebServlet(name = "ActivoServlet", urlPatterns = {"/Activo"})
public class ActivoServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion"); // Obtener la acción
        accion = (accion == null) ? "" : accion; // Si en nulo
        StringBuilder result = new StringBuilder(); // Crear el objeto StringBuilder
        ActivoValidator activoValidator = new ActivoValidator(request);
        JSon jSon = new JSon();

        switch (accion) {
            case "QRY":
                result = activoValidator.activoQry();
                break;
            case "INS":
                result = activoValidator.activoInsUpd(false);
                break;
            case "DEL":
                result = activoValidator.activoDel();
                break;
            case "GET":
                result = activoValidator.activoGet();
                break;
            case "UPD":
                result = activoValidator.activoInsUpd(true);
                break;
            case "FIND":
                result = activoValidator.activoPorNombreQry();
                break;
            case "":
                result = jSon.forMsg("Solicitud no recibida");
                break;

            default:
                result = jSon.forMsg("Solicitud no reconocida");
                break;
        }

        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if ((result == null) || result.length() == 0) {
                result = jSon.forMsg(""); // sin mensaje
            }
            out.print(result);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
