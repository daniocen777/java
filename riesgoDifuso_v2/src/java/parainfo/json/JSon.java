/*
 Utilitaro para tranformar atributos a formato json
 */
package parainfo.json;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author DANIEL
 */
public class JSon {

    /**
     *
     * @param titulo de cada columna de consulta
     * @param list lista de filas de consulta
     * @return StringBuilder
     */
    public StringBuilder forQry(String[] titulo, List<Object[]> list) {
        JSONArray array = new JSONArray();

        JSONObject msg = new JSONObject();
        msg.put("msg", "");
        array.add(msg);

        for (Object[] fil : list) {
            JSONObject obj = new JSONObject();

            for (int i = 0; i < titulo.length; i++) {
                obj.put(titulo[i], fil[i]);
            }

            array.add(obj);
        }

        StringBuilder sb = toSb(array);
        return sb;
    }

    /**
     *
     * @param list lista de filas de consulta
     * @return StringBuilder
     */
    public StringBuilder forQry(List<Object[]> list) {
        JSONArray array = new JSONArray();

        JSONObject msg = new JSONObject();
        msg.put("msg", "");
        array.add(msg);

        for (Object[] fil : list) {
            JSONObject obj = new JSONObject();

            for (int i = 0; i < fil.length; i++) {
                obj.put("col" + (1 + i), fil[i]);
            }

            array.add(obj);
        }

        StringBuilder sb = toSb(array);
        return sb;
    }

    public StringBuilder forCbo(List<Object[]> list) {
        JSONArray array = new JSONArray();

        JSONObject msg = new JSONObject();
        msg.put("msg", "");
        array.add(msg);

        for (Object[] fil : list) {
            JSONObject obj = new JSONObject();

            obj.put("id", fil[0]);
            obj.put("opt", fil[1]);

            array.add(obj);
        }

        StringBuilder sb = toSb(array);
        return sb;
    }

    public StringBuilder forMsg(String mensaje) {
        JSONArray array = new JSONArray();

        JSONObject msg = new JSONObject();
        msg.put("msg", mensaje);
        array.add(msg);

        StringBuilder sb = toSb(array);
        return sb;
    }

    public StringBuilder forMsg(List<String> list) {
        JSONArray array = new JSONArray();

        for (String msg : list) {
            JSONObject obj = new JSONObject();

            obj.put("msg", msg);

            array.add(obj);
        }

        StringBuilder sb = toSb(array);
        return sb;
    }

    public StringBuilder forUpd(String[] titu, Object[] data) {
        JSONArray array = new JSONArray();

        JSONObject msg = new JSONObject();
        msg.put("msg", "");
        array.add(msg);

        JSONObject obj = new JSONObject();
        for (int i = 0; i < titu.length; i++) {
            obj.put(titu[i], data[i]);
        }

        array.add(obj);

        StringBuilder sb = toSb(array);
        return sb;
    }

    /**
     * apoyo
     *
     * @param document recibe JSONObject
     * @return como cadena en un StringBuilder
     */
    private StringBuilder toSb(JSONArray array) {
        StringBuilder sb = null;

        try {
            StringWriter out = new StringWriter();
            array.writeJSONString(out);

            String jsonText = out.toString();
            sb = new StringBuilder(jsonText);

        } catch (IOException ex) {
        }

        return sb;
    }
}
