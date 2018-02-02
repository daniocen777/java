package test;

import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import dto.Activo;
import parainfo.json.JSon;

public class TestJsonForGet {

    public static void main(String[] args) {
        JSon jSon = new JSon();

        DaoActivo daoActivo = new DaoActivoImpl();
        Activo activo = daoActivo.activoGet(5);
        String[] til = {"idactivo", "nombact", "tipoact"};
        Object[] dat = {activo.getIdactivo(), activo.getNombact(), activo.getTipoact()};

        System.out.println(jSon.forUpd(til, dat));
    }

}
