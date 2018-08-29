package dao.impl;

import dao.Dao;
import dao.DaoResponsable;
import dto.Responsable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoResponsableImpl extends Dao implements DaoResponsable {

    @Override
    public List<Responsable> responsablesQry() {
        List<Responsable> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idresponsable,")
                .append("nombre,")
                .append("dni,")
                .append("cargo,")
                .append("celular,")
                .append("correo ")
                .append("FROM responsable ")
                .append("ORDER BY nombre");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Responsable responsable = new Responsable();

                responsable.setIdresponsable(rs.getInt(1));
                responsable.setNombre(rs.getString(2));
                responsable.setDni(rs.getString(3));
                responsable.setCargo(rs.getString(4));
                responsable.setCelular(rs.getString(5));
                responsable.setCorreo(rs.getString(6));

                list.add(responsable);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String responsablesIns(Responsable responsable) {
        sql.delete(0, sql.length())
                .append("INSERT INTO responsable(")
                .append("nombre,")
                .append("dni,")
                .append("cargo,")
                .append("celular,")
                .append("correo")
                .append(") VALUES(?,?,?,?,?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setString(1, responsable.getNombre().toUpperCase());
            ps.setString(2, responsable.getDni());
            ps.setString(3, responsable.getCargo().toUpperCase());
            ps.setString(4, responsable.getCelular());
            ps.setString(5, responsable.getCorreo().toLowerCase());

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? null : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String responsablesDel(Responsable responsable) {
        sql.delete(0, sql.length())
                .append("DELETE FROM responsable WHERE idresponsable = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, responsable.getIdresponsable());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                ok = false;
                message = "ID " + responsable.getIdresponsable() + " no encontrado";
            }

            if (ok) {
                cn.commit();
            } else {
                cn.rollback();
            }
            cn.setAutoCommit(true);

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String responsablesUpd(Responsable responsable) {
        sql.delete(0, sql.length())
                .append("UPDATE responsable SET ")
                .append("nombre = ?,")
                .append("dni = ?,")
                .append("cargo = ?,")
                .append("celular = ?,")
                .append("correo = ? ")
                .append("WHERE idresponsable = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Antes de ejecutar, enviar los parámetros
            ps.setString(1, responsable.getNombre().toUpperCase());
            ps.setString(2, responsable.getDni());
            ps.setString(3, responsable.getCargo().toUpperCase());
            ps.setString(4, responsable.getCelular());
            ps.setString(5, responsable.getCorreo().toLowerCase());
            ps.setInt(6, responsable.getIdresponsable());

            int ctos = ps.executeUpdate(); // Retorna entero
            message = (ctos != 0) ? null : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String responsableDelMult(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM responsable WHERE idresponsable = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            
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
            } else {
                cn.rollback();
            }
            // 2 ==> Final 
            cn.setAutoCommit(true);   // Afectar a la BD

        } catch (SQLException e) {
            message = e.getMessage() + " -- " + "ERROR DE LLAVE FORÁNEA" + " -- ";
        }
        return message;
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
