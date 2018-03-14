package dao;

import dto.CuantRiesgo;
import java.util.List;

public interface DaoCuantRiesgo {

    // Listado de riesgos
    public List<Object[]> cuantRiesgoQry();

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();

    // Método para insertar
    public String cuantRiesgoIns(CuantRiesgo cuantRiesgo);

    // Lista para mostrar gráfico
    public List<Object[]> cuantRiesgoGraph();
}
