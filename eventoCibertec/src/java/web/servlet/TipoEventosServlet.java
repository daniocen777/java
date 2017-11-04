package web.servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import web.validator.TipoEventosValidator;

@WebServlet(name = "TipoEventosServlet", urlPatterns = {"/TipoEventos"})
public class TipoEventosServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String accion = request.getParameter("accion"); // action
        accion = (accion == null) ? "" : accion;
        String target = "tipoEventosQry.jsp";
        List<String> message = new LinkedList<>();
        //
        TipoEventosValidator validator = new TipoEventosValidator(request);

        switch (accion) {
            case "QRY":
                message = validator.tipoEventosQry();
                break;

            case "INS":
                message = validator.tipoEventosInsUpd(false);
                target = message == null
                        ? "TipoEventos?accion=QRY" : "tipoEventosIns.jsp";
                break;

            case "DEL":
                message = validator.tipoEventosDel();
                validator.tipoEventosQry();
                break;

            case "GET":
                message = validator.tipoEventosGet();
                if(message == null) {
                    target = "tipoEventosUpd.jsp";
                } else {
                    validator.tipoEventosQry();
                }
                break;

            case "UPD":
                message = validator.tipoEventosInsUpd(true);
                target = message == null
                        ? "TipoEventos?accion=QRY" : "tipoEventosUpd.jsp";
                break;

            case "":
                message.add("Solicitud no recibida");
                break;

            default:
                message.add("Solicitud no reconocida");
        }

        if (message != null && message.size() > 0) {
            request.setAttribute("message", message);
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(target);
        dispatcher.forward(request, response);
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
