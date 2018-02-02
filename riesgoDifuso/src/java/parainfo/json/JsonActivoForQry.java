package parainfo.json;
import dao.DaoActivo;
import dao.impl.DaoActivoImpl;
import java.util.List;

public class JsonActivoForQry {

    public static void main(String[] args) {
        JSon jSon = new JSon();

        DaoActivo daoActivo = new DaoActivoImpl();
        List<Object[]> list = daoActivo.activoQry();
        String[] titulo = {"idactivo", "nombact", "tipoact"};

        System.out.println(jSon.forQry(titulo, list));
    }
}
