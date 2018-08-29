package parainfo.convert;

import java.util.LinkedList;
import java.util.List;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    // Para ver si es número
    public static boolean isNumeric(String cadena) {
        try {
            Integer.parseInt(cadena);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * @param fecha como cadena
     * @return java.sql.Date
     */
    public static Date aDate(String fecha) {
        Date result = null;

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // Viene con este formato
        sdf.setLenient(false); // Si se permite (50 de febrero) => No tolerante

        try {
            // Tranforma
            java.util.Date utilDate = sdf.parse(fecha); // 1° a Date
            result = new java.sql.Date(utilDate.getTime());

        } catch (ParseException ex) {
        }

        return result; // Si es null, está mal
    }
}
