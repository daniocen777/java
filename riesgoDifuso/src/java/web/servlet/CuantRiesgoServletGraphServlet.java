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
import org.jfree.chart.plot.PiePlot3D;
import org.jfree.data.general.PieDataset;
import web.validator.CuantRiesgoGraphValidator;

@WebServlet(name = "CuantRiesgoServletGraphServlet", urlPatterns = {"/CuantRiesgoServletGraph"})
public class CuantRiesgoServletGraphServlet extends HttpServlet {

    private JFreeChart generaGrafico(PieDataset piedataset) {
        JFreeChart jfreechart = ChartFactory.createPieChart3D(
                "GRÁFICO CIRCULAR-3D RIESGOS",
                piedataset, // datos
                false, // si/no leyenda
                false, // si/no tooltips
                false); // URL

        // adicional
        // Ver http://www.jfree.org/jfreechart/api/javadoc/org/jfree/chart/plot/PiePlot3D.html
        PiePlot3D pieplot3d = (PiePlot3D) jfreechart.getPlot();
        pieplot3d.setBackgroundPaint(Color.decode("#F7F7F7"));
        pieplot3d.setAutoPopulateSectionPaint(true);
        pieplot3d.setLabelPaint(Color.BLUE);
        pieplot3d.setBaseSectionOutlinePaint(Color.WHITE); // Líneas
        //pieplot3d.setLabelBackgroundPaint(Color.decode("#F7F7F7")); 
        //pieplot3d.setLabelLinkPaint(Color.BLUE);
        pieplot3d.setNoDataMessagePaint(Color.BLUE);
        pieplot3d.setOutlinePaint(Color.BLUE);
        pieplot3d.setLabelOutlinePaint(Color.BLUE);
        
        pieplot3d.setDepthFactor(0.10); // Profundidad
        //pieplot3d.setBackgroundAlpha(1.0f);
        return jfreechart;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("image/jpeg");

        CuantRiesgoGraphValidator cuantificar = new CuantRiesgoGraphValidator(request);

        try (OutputStream out = response.getOutputStream()) {
            PieDataset pieDataset = cuantificar.generaDatos();
            JFreeChart grafico = generaGrafico(pieDataset);
            ChartUtilities.writeChartAsJPEG(out, grafico, 600, 300);
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
