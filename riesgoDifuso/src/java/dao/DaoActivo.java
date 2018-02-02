package dao;

import dto.Activo;
import java.util.List;

public interface DaoActivo {

    // Listado de los activos
    public List<Object[]> activoQry();

    // Listado con paginado
    public List<Object[]> activos(int numPag, int filsXpag); // Listado

    public Integer activosPags(int filsXpag); // Cantidad de páginas

    // Listado de los activos por nombre
    public List<Object[]> activoPorNombreQry(String nombact);

    // Método para insertar (ins) los activos
    public String activoIns(Activo activo);

    // Método para eliminar (del) a una activo 
    public String activoDel(List<Integer> ids);

    // Método para actualizar (upd)
    public Activo activoGet(Integer idactivo); // Para buscar un activo

    public String activoUpd(Activo activo); // Actualiza el activo escogido

    // Llenar comboBox
    public List<Object[]> activoCbo();

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
