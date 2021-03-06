/*
 Implementación de la interface DaoActivo
 */
package dao.impl;

import dao.DaoActivo;
import dto.Activo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import log.LOG4J;

/**
 *
 * @author DANIEL
 */
public class DaoActivoImpl extends Dao implements DaoActivo {
   
    @Override
    public List<Object[]> activoQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idactivo,")
                .append("nombactivo,")
                .append("tipo ")
                .append("FROM activo ")
                .append("ORDER BY nombactivo");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[3];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getString(3);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public List<Object[]> activoPorNombreQry(String nombactivo) {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idactivo,")
                .append("nombactivo,")
                .append("tipo ")
                .append("FROM activo ")
                .append("WHERE UPPER(activo.nombactivo) LIKE UPPER('%")
                .append(nombactivo)
                .append("%')")
                .append("ORDER BY nombactivo");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[3];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getString(3);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public String activoIns(Activo activo) {
        sql.delete(0, sql.length())
                .append("INSERT INTO activo(")
                .append("nombactivo,")
                .append("tipo")
                .append(") VALUES(?,?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setString(1, activo.getNombactivo());
            ps.setString(2, activo.getTipo());

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? "ok" : "0 filas afectadas";            
        } catch (SQLException e) {
            message = e.getMessage();            
        }
        return message;
    }

    @Override
    public String activoDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM activo WHERE idactivo = ?");
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
            message = e.getMessage() + " -- " + "Error de llave foránea con activo específico";
        }
        return message;
    }

    @Override
    public Activo activoGet(Integer idactivo) {
        Activo activo = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idactivo,")
                .append("nombactivo,")
                .append("tipo ")
                .append("FROM activo ")
                .append("WHERE idactivo = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Enviar el parámetro antes de ejecutar
            ps.setInt(1, idactivo);
            // Segundo try  
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    activo = new Activo();
                    activo.setIdactivo(rs.getInt(1));
                    activo.setNombactivo(rs.getString(2));
                    activo.setTipo(rs.getString(3));
                } else {
                    message = "Activo no encontrado";
                }
            } catch (SQLException e) {
                message = e.getMessage();
            }
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return activo;
    }

    @Override
    public String activoUpd(Activo activo) {
        sql.delete(0, sql.length())
                .append("UPDATE activo SET ")
                .append("nombactivo = ?,")
                .append("tipo = ? ")
                .append("WHERE idactivo = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Antes de ejecutar, enviar los parámetros
            ps.setString(1, activo.getNombactivo());
            ps.setString(2, activo.getTipo());
            ps.setInt(3, activo.getIdactivo());

            int ctos = ps.executeUpdate(); // Retorna entero
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public List<Object[]> activoCbo() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idactivo,")
                .append("nombactivo,")
                .append("tipo ")
                .append("FROM activo ")
                .append("ORDER BY nombactivo");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Object[] fil = new Object[3];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2); // Nombre de activo
                fil[2] = rs.getString(3);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
