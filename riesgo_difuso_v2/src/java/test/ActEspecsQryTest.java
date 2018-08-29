package test;

import dao.DaoActEspec;
import dao.impl.DaoActEspecImpl;
import dto.ActEspec;
import java.util.List;

public class ActEspecsQryTest {

    public static void main(String[] args) {

        DaoActEspec daoActEspec = new DaoActEspecImpl();

        List<ActEspec> list = daoActEspec.actEspecsQry();

        for (ActEspec act : list) {
            System.out.println("ID: " + act.getIdactespec()
                    + " -- Activo: " + act.getActivo().getNombactivo()
                    + " -- Tipo: " + act.getActivo().getTipo()
                    + " -- Activo Espec.: " + act.getNombactespec());
        }
    }
}
