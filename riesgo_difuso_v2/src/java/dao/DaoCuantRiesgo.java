package dao;

import dto.CuantRiesgo;
import java.util.List;

public interface DaoCuantRiesgo {

    // Listar cuantRiesgos
    public List<CuantRiesgo> cuantRiesgosQry();

    // Insertar nuevos cuantRiesgos
    public String cuantRiesgosIns(CuantRiesgo cuantRiesgo);

    // Insertar masivamente
    //public String cuantRiesgosInsMult(List<CuantRiesgo> listaCuantRiesgo);

    // Eliminar CuantRiesgos
    public String cuantRiesgosDel(CuantRiesgo cuantRiesgo);

    // Editar cuantRiesgo
    public String cuantRiesgosUpd(CuantRiesgo cuantRiesgo);

    // Método para eliminar (del) mpultiples
    public String cuantRiesgoDelMult(List<Integer> ids);

    // Poblar combo => usaremos cuantRiesgosQry
    // Método que devuelve un mensaje de error para el listar
    public String getMessage();
}
