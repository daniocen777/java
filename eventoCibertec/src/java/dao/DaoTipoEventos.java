package dao;

import dto.TipoEventos;
import java.util.List;

public interface DaoTipoEventos {
    
    public List<TipoEventos> tipoEventosQry();
    
    public String tipoEventosIns(TipoEventos tipoEventos);
    
    public String tipoEventosDel(List<Integer> ids);
    
    public TipoEventos tipoEventosGet(Integer idtipoEvento);
    
    public String tipoEventosUpd(TipoEventos tipoEventos);

    public List<Object[]> tipoEventosCbo();
    
    public String getMessage();
}
