package dao;

import dto.Frases;
import java.util.List;

public interface DaoFrases {

    public List<Object[]> frasesQry();
    
    public List<Frases> frasesQry2();
    
    public String frasesIns(Frases frases);
    
    public String frasesDel(List<Integer> ids);
    
    public String frasesDel2(Frases frases);
    
    public Frases frasesGet(Integer idfrase);
    
    public String frasesUpd(Frases frases);
    
    public String getMessage();
}
