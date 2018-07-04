package dao;

import dto.Autores;
import java.util.List;

public interface DaoAutores {

    public List<Object[]> autoresQry();
    
    public List<Autores> autoresQry2();

    public List<Object[]> autoresQry2(Integer numPag, Integer filsXpag);

    public Integer autoresPags(Integer filsXpag);

    public String autoresIns(Autores autores);

    public String autoresDel(List<Integer> ids);
    
    public String autoresDel2(Autores autores);

    public Autores autoresGet(Integer idautor);

    public String autoresUpd(Autores autores);

    public List<Object[]> autoresCbo();
    
    public List<Autores> autoresCbo2();

    public String getMessage();
}
