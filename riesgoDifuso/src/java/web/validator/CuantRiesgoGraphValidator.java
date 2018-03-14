package web.validator;

import dao.DaoCuantRiesgo;
import dao.impl.DaoCuantRiesgoImpl;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import parainfo.json.JSon;

public class CuantRiesgoGraphValidator {

    private final HttpServletRequest request;
    JSon jSon = new JSon();
    DaoCuantRiesgo daoCuantRiesgoImpl;

    public CuantRiesgoGraphValidator(HttpServletRequest request) {
        this.request = request;
        this.daoCuantRiesgoImpl = new DaoCuantRiesgoImpl();
    }

    // Método para generar los datos de la gráfica
    public PieDataset generaDatos() {
        List<Object[]> list = daoCuantRiesgoImpl.cuantRiesgoGraph();
        Integer contadorB = 0;
        Integer contadorMA = 0;
        Integer contadorA = 0;
        Integer contadorMB = 0;
        Integer contadorM = 0;

        DefaultPieDataset datos = new DefaultPieDataset();
        for (int i = 0; i < list.size(); i++) {
            //Double valorRiesgo = (Double) list.get(i)[3];
            String riesgo = (String) list.get(i)[4];
            if (riesgo.equalsIgnoreCase("BAJO")) {
                contadorB += 1;
            }
            if (riesgo.equalsIgnoreCase("MUY ALTO")) {
                contadorMA += 1;
            }

            if (riesgo.equalsIgnoreCase("ALTO")) {
                contadorA += 1;
            }

            if (riesgo.equalsIgnoreCase("MUY BAJO")) {
                contadorA += 1;
            }

            if (riesgo.equalsIgnoreCase("MEDIO")) {
                contadorM += 1;
            }

        }
        datos.setValue("MUY BAJO" + " - " + contadorMB, contadorMB);
        datos.setValue("BAJO" + " - " + contadorB, contadorB);
        datos.setValue("MEDIO" + " - " + contadorM, contadorM);
        datos.setValue("ALTO" + " - " + contadorA, contadorA);
        datos.setValue("MUY ALTO" + " - " + contadorMA, contadorMA);

        return datos;
    }

    // Gráfico de barras
    public CategoryDataset generaDatosGraphBarras() {
        List<Object[]> list = daoCuantRiesgoImpl.cuantRiesgoGraph();
        Integer contadorB = 0;
        Integer contadorMA = 0;
        Integer contadorA = 0;
        Integer contadorMB = 0;
        Integer contadorM = 0;

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < list.size(); i++) {
            //Double valorRiesgo = (Double) list.get(i)[3];
            String riesgo = (String) list.get(i)[4];
            if (riesgo.equalsIgnoreCase("BAJO")) {
                contadorB += 1;
            }
            if (riesgo.equalsIgnoreCase("MUY ALTO")) {
                contadorMA += 1;
            }

            if (riesgo.equalsIgnoreCase("ALTO")) {
                contadorA += 1;
            }

            if (riesgo.equalsIgnoreCase("MUY BAJO")) {
                contadorA += 1;
            }

            if (riesgo.equalsIgnoreCase("MEDIO")) {
                contadorM += 1;
            }

        }
        dataset.setValue(contadorMB, "MUY BAJO", "");
        dataset.setValue(contadorB, "BAJO", "");
        dataset.setValue(contadorM, "MEDIO", "");
        dataset.setValue(contadorA, "ALTO", "");
        dataset.setValue(contadorMA, "MUY ALTO", "");

        return dataset;
    }

}
