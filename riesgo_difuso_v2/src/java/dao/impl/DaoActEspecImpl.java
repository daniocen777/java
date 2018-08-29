package dao.impl;

import dao.Dao;
import dao.DaoActEspec;
import dto.ActEspec;
import dto.Activo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoActEspecImpl extends Dao implements DaoActEspec {

    @Override
    public List<ActEspec> actEspecsQry() {
        List<ActEspec> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespec.idactespec,") // 1
                .append("activo.idactivo,") // 2
                .append("activo.nombactivo,") // 3
                .append("activo.tipo,") // 4
                .append("actespec.nombactespec ") // 5
                .append("FROM actespec ")
                .append("INNER JOIN activo ")
                .append("ON actespec.activo_idactivo = activo.idactivo ")
                .append("ORDER BY activo.nombactivo");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                ActEspec actEspec = new ActEspec();
                Activo activo = new Activo();
                activo.setIdactivo(rs.getInt(2));
                activo.setNombactivo(rs.getString(3));
                activo.setTipo(rs.getString(4));

                actEspec.setIdactespec(rs.getInt(1));
                actEspec.setActivo(activo);
                actEspec.setNombactespec(rs.getString(5));

                list.add(actEspec);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public List<ActEspec> actEspecsQryXActivo(Integer id) {
        List<ActEspec> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("actespec.idactespec,") // 1
                .append("activo.idactivo,") // 2
                .append("activo.nombactivo,") // 3
                .append("activo.tipo,") // 4
                .append("actespec.nombactespec ") // 5
                .append("FROM actespec ")
                .append("INNER JOIN activo ")
                .append("ON actespec.activo_idactivo = activo.idactivo ")
                .append("WHERE activo.idactivo = ? ")
                .append("ORDER BY activo.nombactivo");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            list = new LinkedList<>();

            while (rs.next()) {
                ActEspec actEspec = new ActEspec();
                Activo activo = new Activo();
                activo.setIdactivo(rs.getInt(2));
                activo.setNombactivo(rs.getString(3));
                activo.setTipo(rs.getString(4));

                actEspec.setIdactespec(rs.getInt(1));
                actEspec.setActivo(activo);
                actEspec.setNombactespec(rs.getString(5));

                list.add(actEspec);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String actEspecsIns(ActEspec actEspec) {
        sql.delete(0, sql.length())
                .append("INSERT INTO actespec(")
                .append("activo_idactivo,")
                .append("nombactespec ")
                .append(") VALUES(?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, actEspec.getActivo().getIdactivo());
            ps.setString(2, actEspec.getNombactespec().toUpperCase());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "0 filas afectadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String actEspecsInsMult(List<ActEspec> listaActEspec) {
        sql.delete(0, sql.length())
                .append("INSERT INTO actespec(")
                .append("activo_idactivo,")
                .append("nombactespec ")
                .append(") VALUES(?, ?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false); // No afectar a la BD
            // 3 ==> Flag para saber si es correcto
            boolean ok = true;
            for (ActEspec actEsp : listaActEspec) {
                ps.setInt(1, actEsp.getActivo().getIdactivo()); // Parámetro nombre de activo
                ps.setString(2, actEsp.getNombactespec().toUpperCase()); // Parámetro nombre de activo
                int ctos = ps.executeUpdate(); // Ejecutar
                if (ctos == 0) {
                    ok = false;
                    message = "Error al insertar activo " + actEsp.getNombactespec();
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
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String actEspecsDel(ActEspec actEspec) {
        sql.delete(0, sql.length())
                .append("DELETE FROM actespec WHERE idactespec = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, actEspec.getIdactespec());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                ok = false;
                message = "ID " + actEspec.getIdactespec() + " no encontrado";
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
    public String actEspecsUpd(ActEspec actEspec) {
        sql.delete(0, sql.length())
                .append("UPDATE actespec SET ")
                .append("activo_idactivo = ?,")
                .append("nombactespec = ? ")
                .append("WHERE idactespec = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, actEspec.getActivo().getIdactivo());
            ps.setString(2, actEspec.getNombactespec().toUpperCase());
            ps.setInt(3, actEspec.getIdactespec());

            int ctos = ps.executeUpdate();
            if (ctos == 0) {
                message = "0 filas afectadas";
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return message;
    }

    @Override
    public String actEspecDelMult(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM actespec WHERE idactespec = ?");
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
        return message;
    }

}
