package dao;

import dto.Salvaguarda;
import java.util.List;

public interface DaoSalvaguarda {

    // Listar salvaguardas
    public List<Salvaguarda> salvaguardasQry();

    // Listar salvaguardas por ID activo específico
    public List<Salvaguarda> salvaguardasQryXActEspec(Integer idActivo, Integer idActEspec);

    // Insertar nuevos salvaguardas
    public String salvaguardasIns(Salvaguarda salvaguarda);

    // Eliminar Salvaguardas
    public String salvaguardasDel(Salvaguarda salvaguarda);

    // Editar salvaguarda
    public String salvaguardasUpd(Salvaguarda salvaguarda);

    // Método para eliminar (del) mpultiples
    public String salvaguardaDelMult(List<Integer> ids);

    // Poblar combo => usaremos salvaguardasQry
    // Método que devuelve un mensaje de error para el listar
    public String getMessage();

}
