/*
 Interface para los activos específicos
  Aquí estarán los métodos a implementat
 */
package dao;

import dto.ActEspec;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public interface DaoActEspec {

    public List<Object[]> actEspecQry(); // Listado de los actEspecs

    // Listado de los activos específicos por nombre
    public List<Object[]> activoEspecXNombreQry(String nombactespec);

    public String actEspecIns(ActEspec actEspec); // insertar (ins) activos específicos

    public String actEspecDel(List<Integer> ids); // eliminar (del) a una actEspec 

    // Método para actualizar (upd)
    public ActEspec actEspecGet(Integer idactEspec); // Para buscar un actEspec

    public String actEspecUpd(ActEspec actEspec); // Actualiza el actEspec escogido

    public List<Object[]> activoEspecCbo(); // Llenar comboBox de activos específicos

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
