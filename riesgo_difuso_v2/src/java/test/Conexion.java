package test;

import java.sql.Connection;
import java.sql.SQLException;
import parainfo.sql.ConectaDb;

public class Conexion {

    public static void main(String[] args) {

        ConectaDb conexion = new ConectaDb();

        try {
            Connection cn = conexion.getConnection();

            if (cn != null) {
                System.out.println("Sí");
            } else {
                System.out.println("NO");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
