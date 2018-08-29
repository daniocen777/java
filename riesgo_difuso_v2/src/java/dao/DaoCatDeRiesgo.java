package dao;

import dto.CatDeRiesgo;
import java.util.List;

public interface DaoCatDeRiesgo {

    // Listar categorias de los riesgos o amenazas
    public List<CatDeRiesgo> catDeRiesgoQry();

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
