package dao.impl;

import dao.DaoAmenaza;
import dto.Amenaza;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoAmenazaImpl implements DaoAmenaza {

    // Cabecera
    private ConectaDb db;
    private StringBuilder sql;
    private String message;

    public DaoAmenazaImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> amenazaQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idamenaza,")
                .append("nombamen ")
                .append("FROM amenaza ")
                .append("ORDER BY nombamen");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {
            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[2];
                // Poblar la lista
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    // Método para insertar los amenazas
    @Override
    public String amenazaIns(Amenaza amenaza) {

        sql.delete(0, sql.length())
                .append("INSERT INTO amenaza(")
                .append("nombamen")
                .append(") VALUES(?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setString(1, amenaza.getNombamen());

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? "ok" : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    // Método para eliminar un amenaza
    @Override
    public String amenazaDel(List<Integer> ids) {

        sql.delete(0, sql.length())
                .append("DELETE FROM amenaza WHERE idamenaza = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Se necesita el concepto de transacción, ya que no eliminará si hay algún "ssid" errado
            // 1 ==> Inicio
            cn.setAutoCommit(false); // No afectar a la BD
            // 3 ==> Flag para saber si es correcto
            boolean ok = true;
            for (Integer id : ids) {
                ps.setInt(1, id);
                int ctos = ps.executeUpdate(); // Ejecutar
                if (ctos == 0) {
                    ok = false;
                    message = "ID " + id + " no encontrado";
                    break;
                }
            }
            if (ok) {
                cn.commit();
                message = "ok";
            } else {
                cn.rollback();
            }

            // 2 ==> Final 
            cn.setAutoCommit(true);   // Afectar a la BD

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public Amenaza amenazaGet(Integer idamenaza) {
        Amenaza amenaza = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idamenaza,")
                .append("nombamen ")
                .append("FROM amenaza ")
                .append("WHERE idamenaza = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Enviar el parámetro antes de ejecutar
            ps.setInt(1, idamenaza);
            // Segundo try  
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    amenaza = new Amenaza();
                    amenaza.setIdamenaza(rs.getInt(1));
                    amenaza.setNombamen(rs.getString(2));
                }
            } catch (SQLException e) {
                message = e.getMessage();
            }
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return amenaza;
    }

    @Override
    public String amenazaUpd(Amenaza amenaza) {
        sql.delete(0, sql.length())
                .append("UPDATE amenaza SET ")
                .append("nombamen = ?")
                .append(" WHERE idamenaza = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Antes de ejecutar, enviar los parámetros
            ps.setString(1, amenaza.getNombamen());
            ps.setInt(2, amenaza.getIdamenaza());

            int ctos = ps.executeUpdate(); // Retorna entero
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public List<Object[]> amenazaPorNombreQry(String nombamen) {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idamenaza,")
                .append("nombamen ")
                .append("FROM amenaza ")
                .append("WHERE UPPER(amenaza.nombamen) LIKE UPPER('%")
                .append(nombamen)
                .append("%')")
                .append("ORDER BY nombamen");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[2];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public List<Object[]> amenazaCbo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        return message;
    }

}
