package dao.impl;

import dao.DaoTipoEventos;
import dto.TipoEventos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoTipoEventosImpl implements DaoTipoEventos {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoTipoEventosImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<TipoEventos> tipoEventosQry() {
        List<TipoEventos> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idtipoevento,") // 
                .append("tipoevento,")
                .append("descripciontipo ")
                .append("FROM tipoeventos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                TipoEventos tipoEventos = new TipoEventos();

                tipoEventos.setIdtipoevento(rs.getInt(1));
                tipoEventos.setTipoevento(rs.getString(2));
                tipoEventos.setDescripciontipo(rs.getString(3));

                list.add(tipoEventos);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public String tipoEventosIns(TipoEventos tipoEventos) {
        sql.delete(0, sql.length())
                .append("INSERT INTO tipoeventos(")
                .append("tipoevento,")
                .append("descripciontipo")
                .append(") VALUES(?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, tipoEventos.getTipoevento());
            ps.setString(2, tipoEventos.getDescripciontipo());

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
    public String tipoEventosDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM tipoeventos WHERE idtipoevento = ?");

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
    public TipoEventos tipoEventosGet(Integer idtipoEvento) {
        TipoEventos tipoEventos = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idtipoevento,")
                .append("tipoevento,")
                .append("descripciontipo ")
                .append("FROM tipoeventos ")
                .append("WHERE idtipoevento = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idtipoEvento);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    tipoEventos = new TipoEventos();

                    tipoEventos.setIdtipoevento(rs.getInt(1));
                    tipoEventos.setTipoevento(rs.getString(2));
                    tipoEventos.setDescripciontipo(rs.getString(3));

                } else {
                    message = "Registro no encontrado";
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return tipoEventos;
    }

    @Override
    public String tipoEventosUpd(TipoEventos tipoEventos) {
        sql.delete(0, sql.length())
                .append("UPDATE tipoeventos SET ")
                .append("tipoevento = ?,")
                .append("descripciontipo = ? ")
                .append("WHERE idtipoevento = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, tipoEventos.getTipoevento());
            ps.setString(2, tipoEventos.getDescripciontipo());
            ps.setInt(3, tipoEventos.getIdtipoevento());

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
    public List<Object[]> tipoEventosCbo() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idtipoevento,") // 
                .append("tipoevento,")
                .append("descripciontipo ")
                .append("FROM tipoeventos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

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
    public String getMessage() {
        return message;
    }

}
