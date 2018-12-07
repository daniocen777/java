package parainfo.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConectaDb {

    public Connection getConnection() throws SQLException{
        // Afuera la conexión
        Connection cn = null;
        try {
            // Sólo dos líneas
            // instanciar la clase, que llama a otras clases, que es el "DriverManager"
            Class.forName(driver).newInstance();
            cn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            throw new SQLException(ex.getMessage());
        }
        return cn;
    }

    private final String url = "jdbc:mysql://localhost:3306/parainfo";
    // Paquete ==> Libraries/com/mysql... Driver (Clase)
    private final String driver = "com.mysql.jdbc.Driver";
    private final String user = "root";
    private final String password = "";
}
