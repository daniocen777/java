package dao;

import dto.Amenaza;
import java.util.List;

public interface DaoAmenaza {

    // Listar amenazas
    public List<Amenaza> amenazasQry();

    // Insertar nuevos amenazas
    public String amenazasIns(Amenaza amenaza);

    // Eliminar Amenazas
    public String amenazasDel(Amenaza amenaza);

    // Editar amenaza
    public String amenazasUpd(Amenaza amenaza);

    // Método para eliminar (del) mpultiples
    public String amenazaDelMult(List<Integer> ids);

    // Poblar combo => usaremos amenazasQry
    // Método que devuelve un mensaje de error para el listar
    public String getMessage();

}
