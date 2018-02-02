package dao;

import dto.Amenaza;
import java.util.List;

public interface DaoAmenaza {
    
    // Listado de los amenazas
    public List<Object[]> amenazaQry();
    
     // Listado de amenazas por nombre
    public List<Object[]> amenazaPorNombreQry(String nombamen);

    // Método para insertar (ins) los amenazas
    public String amenazaIns(Amenaza amenaza);

    // Método para eliminar (del) a una amenaza 
    public String amenazaDel(List<Integer> ids);

    // Método para actualizar (upd)
    public Amenaza amenazaGet(Integer idamenaza); // Para buscar un amenaza

    public String amenazaUpd(Amenaza amenaza); // Actualiza el amenaza escogido
    
    // Llenar comboBox
    public List<Object[]> amenazaCbo();

    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
