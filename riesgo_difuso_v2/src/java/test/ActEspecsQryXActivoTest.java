package test;

import dao.DaoActEspec;
import dao.impl.DaoActEspecImpl;
import dto.ActEspec;
import java.util.List;

public class ActEspecsQryXActivoTest {
    
    public static void main(String[] args) {
        
        DaoActEspec daoActEspec = new DaoActEspecImpl();
        
        List<ActEspec> list = daoActEspec.actEspecsQryXActivo(14);
        
        for (ActEspec act : list) {
            System.out.println(act.getNombactespec());
        }
    }
}
