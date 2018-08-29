package dao;

import dto.ActEspec;
import java.util.List;

public interface DaoActEspec {

    // Listar actEspecs
    public List<ActEspec> actEspecsQry();

    // Listar actEspecs pos activo
    public List<ActEspec> actEspecsQryXActivo(Integer id);

    // Insertar nuevos actEspecs
    public String actEspecsIns(ActEspec actEspec);

    // Insertar masivamente
    public String actEspecsInsMult(List<ActEspec> listaActEspec);

    // Eliminar ActEspecs
    public String actEspecsDel(ActEspec actEspec);

    // Editar actEspec
    public String actEspecsUpd(ActEspec actEspec);

    // Método para eliminar (del) mpultiples
    public String actEspecDelMult(List<Integer> ids);

    // Poblar combo => usaremos actEspecsQry
    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
