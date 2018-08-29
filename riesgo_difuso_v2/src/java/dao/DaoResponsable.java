package dao;

import dto.Responsable;
import java.util.List;

public interface DaoResponsable {

    // Listar responsables
    public List<Responsable> responsablesQry();

    // Insertar nuevos responsables
    public String responsablesIns(Responsable responsable);

    // Eliminar Responsables
    public String responsablesDel(Responsable responsable);

    // Editar responsable
    public String responsablesUpd(Responsable responsable);

    // Método para eliminar (del) mpultiples
    public String responsableDelMult(List<Integer> ids);

    // Poblar combo => usaremos responsablesQry
    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
