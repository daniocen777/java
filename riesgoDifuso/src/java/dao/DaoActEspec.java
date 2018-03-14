package dao;

import dto.ActEspec;
import java.util.List;

public interface DaoActEspec {

    // Listado de los actEspecs
    public List<Object[]> actEspecQry();

    // Listado de los activos específicos por nombre
    public List<Object[]> activoEspecPorNombreQry(String nombactespec);

    // Método para insertar (ins) los actEspecs
    public String actEspecIns(ActEspec actEspec);

    // Método para eliminar (del) a una actEspec 
    public String actEspecDel(List<Integer> ids);

    // Método para actualizar (upd)
    public ActEspec actEspecGet(Integer idactEspec); // Para buscar un actEspec

    // Get
    public Object[] actEspecGet2(Integer idactEspec);

    public String actEspecUpd(ActEspec actEspec); // Actualiza el actEspec escogido

    // Llenar comboBox
    public List<Object[]> activoEspecCbo();

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
