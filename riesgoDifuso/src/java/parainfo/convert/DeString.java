package parainfo.convert;

import java.util.LinkedList;
import java.util.List;

public class DeString {

    // Ya no se necesita declarar el objeto
    public static Integer toInteger(String str) {
        Integer result = null;
        if (str != null) {
            // Intentar convertir a entero
            try {
                result = Integer.valueOf(str);
            } catch (NumberFormatException e) {

            }
        }
        return result;
    }

    public static List<Integer> ids(String _ids) {
        List<Integer> list = null;

        if ((_ids != null) && (_ids.trim().length() > 0)) {
            String[] id = _ids.split(",");

            list = new LinkedList<>();
            for (String ix : id) {
                Integer x = toInteger(ix);

                if (x != null) {
                    list.add(x);
                } else {
                    list = null;
                    break;
                }
            }
        }

        return list;
    }
}
