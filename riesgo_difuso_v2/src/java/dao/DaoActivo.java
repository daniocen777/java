package dao;

import dto.Activo;
import java.util.List;

public interface DaoActivo {

    // Listar activos
    public List<Activo> activosQry();

    // Insertar nuevos activos
    public String activosIns(Activo activo);

    // Insertar nuevos activos masivamente
    public String activosInsMult(List<Activo> liataActivos);

    // Eliminar Activos
    public String activosDel(Activo activo);

    // Editar activo
    public String activosUpd(Activo activo);

    // Método para eliminar (del) mpultiples
    public String activoDelMult(List<Integer> ids);

    // Buscar activo por ID
    public String activoXId(Integer id);

    // Poblar combo => usaremos activosQry
    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
