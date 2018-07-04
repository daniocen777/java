/*
 Implementación de la interfaz DaoActEspec
 */
package dao.impl;

import dao.DaoActEspec;
import dto.ActEspec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public class DaoActEspecImpl extends Dao implements DaoActEspec {

    @Override
    public List<Object[]> actEspecQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespec.idactespec,")
                .append("activo.nombactivo,")
                .append("actespec.nombactespec ")
                .append("FROM actespec ")
                .append("INNER JOIN activo ")
                .append("ON actespec.idactivo = activo.idactivo ")
                .append("ORDER BY activo.nombactivo");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {
            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[3];
                fil[0] = rs.getInt(1); // idactespec
                fil[1] = rs.getString(2); // nombactivo
                fil[2] = rs.getString(3); // nombactespec

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public List<Object[]> activoEspecXNombreQry(String nombactespec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String actEspecIns(ActEspec actEspec) {
        sql.delete(0, sql.length())
                .append("INSERT INTO actespec(")
                .append("idactivo,")
                .append("nombactespec")
                .append(") VALUES(?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, actEspec.getIdactivo());
            ps.setString(2, actEspec.getNombactespec());

            int ctos = ps.executeUpdate();
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String actEspecDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM actespec WHERE idactespec = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            for (Integer id : ids) {
                ps.setInt(1, id);

                int ctos = ps.executeUpdate();
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
            cn.setAutoCommit(true);

        } catch (SQLException e) {
            message = e.getMessage() + " -- " + "ERROR DE LLAVE FORÁNEA" + " -- ";
        }

        return message;
    }

    @Override
    public ActEspec actEspecGet(Integer idactEspec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String actEspecUpd(ActEspec actEspec) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> activoEspecCbo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        return message;
    }

}
