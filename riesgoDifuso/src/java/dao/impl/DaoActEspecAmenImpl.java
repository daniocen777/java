package dao.impl;

import dao.DaoActEspecAmen;
import dto.ActEspecAmen;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoActEspecAmenImpl implements DaoActEspecAmen {

    // Cabecera
    private ConectaDb db;
    private StringBuilder sql;
    private String message;

    public DaoActEspecAmenImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> actEspecAmenQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespecamen.idactespecamen,")
                .append("activo.nombact,")
                .append("actespec.nombactespec,")
                .append("amenaza.nombamen ")
                .append("FROM actespecamen ")
                .append("INNER JOIN actespec ON actespecamen.idactespec = actespec.idactespec ")
                .append("INNER JOIN activo ON actespec.idactivo = activo.idactivo ")
                .append("INNER JOIN amenaza ON actespecamen.idamenaza = amenaza.idamenaza ")
                .append("ORDER BY activo.nombact");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {
            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[4];
                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getString(3);
                fil[3] = rs.getString(4);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public String actEspecAmenIns(ActEspecAmen actEspecAmen) {
        sql.delete(0, sql.length())
                .append("INSERT INTO actespecamen(")
                .append("idactespec,")
                .append("idamenaza")
                .append(") VALUES(?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, actEspecAmen.getIdactespec());
            ps.setInt(2, actEspecAmen.getIdamenaza());

            int ctos = ps.executeUpdate();
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String actEspecAmenDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM actespecamen WHERE idactespecamen = ?");

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
    public ActEspecAmen actEspecAmenGet(Integer idactEspecAmen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object[] actEspecAmenGet2(Integer idactEspecAmen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String actEspecAmenUpd(ActEspecAmen actEspecAmen) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> actEspecAmenCbo() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespecamen.idactespecamen,")
                .append("activo.nombact,")
                .append("actespec.nombactespec,")
                .append("amenaza.nombamen ")
                .append("FROM actespecamen ")
                .append("INNER JOIN amenaza ON actespecamen.idamenaza = amenaza.idamenaza ")
                .append("INNER JOIN actespec ON actespecamen.idactespec = actespec.idactespec ")
                .append("INNER JOIN activo ON actespec.idactivo = activo.idactivo ")
                .append("ORDER BY activo.nombact");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Object[] fil = new Object[4];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getString(3);
                fil[3] = rs.getString(4);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String getMessage() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
