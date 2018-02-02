package dao.impl;

import dao.DaoActEspec;
import dto.ActEspec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoActEspecImpl implements DaoActEspec {

    // Cabecera
    private ConectaDb db;
    private StringBuilder sql;
    private String message;

    public DaoActEspecImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> actEspecQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespec.idactespec,")
                .append("activo.nombact,")
                .append("actespec.nombactespec ")
                .append("FROM actespec ")
                .append("INNER JOIN activo ")
                .append("ON actespec.idactivo = activo.idactivo ")
                .append("ORDER BY actespec.nombactespec");
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
            message = e.getMessage() + " -- " + "Error de llave foránea con activo específico";
        }

        return message;
    }

    @Override
    public ActEspec actEspecGet(Integer idactEspec) {
        ActEspec actEspec = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idactespec,")
                .append("idactivo,")
                .append("nombactespec ")
                .append("FROM actespec ")
                .append("WHERE idactespec = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idactEspec);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    actEspec = new ActEspec();

                    actEspec.setIdactespec(rs.getInt(1));
                    actEspec.setIdactivo(rs.getInt(2));
                    actEspec.setNombactespec(rs.getString(3));

                } else {
                    message = "Registro no encontrado";
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return actEspec;
    }

    @Override
    public String actEspecUpd(ActEspec actEspec) {
        sql.delete(0, sql.length())
                .append("UPDATE actespec SET ")
                .append("idactivo= ?,")
                .append("nombactespec = ? ")
                .append("WHERE idactespec = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, actEspec.getIdactivo());
            ps.setString(2, actEspec.getNombactespec());
            ps.setInt(3, actEspec.getIdactespec());

            int ctos = ps.executeUpdate();
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public List<Object[]> activoEspecPorNombreQry(String nombactespec) {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespec.idactespec,")
                .append("activo.nombact,")
                .append("actespec.nombactespec ")
                .append("FROM actespec ")
                .append("INNER JOIN activo ")
                .append("ON actespec.idactivo = activo.idactivo ")
                .append("WHERE UPPER(actespec.nombactespec) LIKE UPPER('%")
                .append(nombactespec)
                .append("%')")
                .append("ORDER BY actespec.nombactespec");
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
    public Object[] actEspecGet2(Integer idactEspec) {
        Object[] list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespec.idactespec,")
                .append("activo.nombact,")
                .append("actespec.nombactespec ")
                .append("FROM actespec ")
                .append("INNER JOIN activo ")
                .append("ON actespec.idactivo = activo.idactivo ")
                .append("WHERE idactespec = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {

            ps.setInt(1, idactEspec);
            try (ResultSet rs = ps.executeQuery()) {
                list = new Object[3]; // Instanciar la lista
                if (rs.next()) {
                    list[0] = rs.getInt(1);
                    list[1] = rs.getString(2);
                    list[2] = rs.getString(3);
                }
            } catch (Exception e) {
                message = e.getMessage();
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
