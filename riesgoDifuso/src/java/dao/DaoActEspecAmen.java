package dao;

import dto.ActEspecAmen;
import java.util.List;

public interface DaoActEspecAmen {

    // Listado de los actEspecs
    public List<Object[]> actEspecAmenQry();

    // Listado de los activos específicos por nombre
    //public List<Object[]> activoEspecAmenPorNombreQry(String nombactespec);
    // Método para insertar (ins) los actEspecs
    public String actEspecAmenIns(ActEspecAmen actEspecAmen);

    // Método para eliminar (del) a una actEspec 
    public String actEspecAmenDel(List<Integer> ids);

    // Método para actualizar (upd)
    public ActEspecAmen actEspecAmenGet(Integer idactEspecAmen); // Para buscar un actEspec

    // Get
    public Object[] actEspecAmenGet2(Integer idactEspecAmen);

    public String actEspecAmenUpd(ActEspecAmen actEspecAmen); // Actualiza el actEspec escogido
    
    // Llenar comboBox
    public List<Object[]> actEspecAmenCbo();

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
