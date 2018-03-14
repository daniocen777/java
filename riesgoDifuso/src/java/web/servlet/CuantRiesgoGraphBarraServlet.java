package web.servlet;

import java.awt.Color;
import java.io.IOException;
import java.io.OutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import web.validator.CuantRiesgoGraphValidator;

@WebServlet(name = "CuantRiesgoGraphBarraServlet", urlPatterns = {"/CuantRiesgoGraphBarra"})
public class CuantRiesgoGraphBarraServlet extends HttpServlet {

    private JFreeChart generaGrafico(final CategoryDataset dataset) {
        // Personalización del gráfico
        JFreeChart jfreechart = ChartFactory.createBarChart3D(
                "GRÁFICO DE BARRAS", "RIESGO",
                "CANTIDAD", dataset,
                PlotOrientation.VERTICAL,
                true, // si/no leyenda
                false, // si/no tooltips
                false); // URL

        CategoryPlot plot = jfreechart.getCategoryPlot();
        plot.setBackgroundPaint(Color.decode("#F7F7F7"));
        
        plot.setRangeGridlinePaint(Color.GRAY);

        return jfreechart;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");
        try (OutputStream out = response.getOutputStream()) {
            CuantRiesgoGraphValidator cuantificar = new CuantRiesgoGraphValidator(request);
            CategoryDataset dataset = cuantificar.generaDatosGraphBarras();
            JFreeChart jfc = generaGrafico(dataset);
            ChartUtilities.writeChartAsJPEG(out, jfc, 500, 600);
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
