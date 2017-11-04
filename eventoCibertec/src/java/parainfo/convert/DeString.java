package parainfo.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DeString {

    public static Integer toInteger(String str) {
        Integer result = null;

        if (str != null) {

            try {
                result = Integer.valueOf(str);
            } catch (NumberFormatException e) {
            }
        }

        return result;
    }

    public static Double toDouble(String str) {
        Double result = null;

        if (str != null) {

            try {
                result = Double.valueOf(str);
            } catch (NumberFormatException e) {
            }
        }

        return result;
    }

    public static List<Integer> ids(String str) {
        List<Integer> list = null;

        if (str != null) {
            list = new LinkedList<>();

            String[] id = str.split(",");

            for (String s : id) {
                try {
                    Integer x = Integer.valueOf(s);
                    list.add(x);

                } catch (NumberFormatException e) {
                    list = null;
                    break;
                }
            }
        }

        return list;
    }

    public static Date toDate(String strFecha) {
        Date fecha = null;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy H:mm a");
        try {
            fecha = sdf.parse(strFecha);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return fecha;
    }
}
