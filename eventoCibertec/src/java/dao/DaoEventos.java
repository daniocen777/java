package dao;

import dto.Eventos;
import java.util.List;

public interface DaoEventos {
    
     public List<Object[]> eventosQry();
    
    public String eventosIns(Eventos eventos);
    
    public String eventosDel(List<Integer> ids);
    
    public Eventos eventosGet(Integer idevento);
    
    public String eventosUpd(Eventos eventos);
    
    public String getMessage();
}
