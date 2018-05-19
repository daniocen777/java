/*
 Interface para la clase Activo
 Acá se visualiza lo que se implementará
 */
package dao;

import dto.Activo;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public interface DaoActivo {

    public List<Object[]> activoQry(); // Listado de los activos

    public List<Object[]> activoPorNombreQry(String nombactivo); // Listado de los activos por nombre

    public String activoIns(Activo activo); // Método para insertar (ins) los activos

    public String activoDel(List<Integer> ids); // Método para eliminar (del) a una activo 

    // Método para actualizar (upd)
    public Activo activoGet(Integer idactivo); // Para buscar un activo

    public String activoUpd(Activo activo); // Actualiza el activo escogido

    public List<Object[]> activoCbo(); // Llenar comboBox

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
