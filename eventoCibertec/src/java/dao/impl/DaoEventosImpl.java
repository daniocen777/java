package dao.impl;

import dao.DaoEventos;
import dto.Eventos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoEventosImpl implements DaoEventos {

    private final ConectaDb db;
    private final StringBuilder sql;
    private String message;

    public DaoEventosImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> eventosQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("eventos.idevento,")
                .append("tipoeventos.tipoevento,")
                .append("tipoeventos.descripciontipo,")
                .append("eventos.evento,")
                .append("eventos.descripcionevento,")
                .append("DATE_FORMAT(eventos.comienzo, '%d-%m-%Y %l:%i %p'),")
                .append("DATE_FORMAT(eventos.fin, '%d-%m-%Y %l:%i %p'),")
                .append("eventos.detalle ")
                .append("FROM eventos ")
                .append("INNER JOIN tipoeventos ")
                .append("ON eventos.idtipoevento = tipoeventos.idtipoevento");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Object[] fil = new Object[8];

                fil[0] = rs.getInt(1);
                fil[1] = rs.getString(2);
                fil[2] = rs.getString(3);
                fil[3] = rs.getString(4);
                fil[4] = rs.getString(5);
                fil[5] = rs.getString(6);
                fil[6] = rs.getString(7);
                fil[7] = rs.getString(8);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String eventosIns(Eventos eventos) {
        sql.delete(0, sql.length())
                .append("INSERT INTO eventos(")
                .append("idtipoevento,")
                .append("evento,")
                .append("descripcionevento,")
                .append("comienzo,")
                .append("fin,")
                .append("detalle")
                .append(") VALUES(?, ?, ?, ?, ?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, eventos.getIdtipoevento());
            ps.setString(2, eventos.getEvento());
            ps.setString(3, eventos.getDescripcionevento());
            ps.setString(4, eventos.getComienzo());
            ps.setString(5, eventos.getFin());
            ps.setString(6, eventos.getDetalle());

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
    public String eventosDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM eventos WHERE idevento = ?");

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
    public Eventos eventosGet(Integer idevento) {
        Eventos eventos = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idevento,")
                .append("idtipoevento,")
                .append("evento,")
                .append("descripcionevento,")
                .append("comienzo,")
                .append("fin,")
                .append("detalle ")
                .append("FROM eventos ")
                .append("WHERE idevento = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idevento);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    eventos = new Eventos();

                    eventos.setIdevento(rs.getInt(1));
                    eventos.setIdtipoevento(rs.getInt(2));
                    eventos.setEvento(rs.getString(3));
                    eventos.setDescripcionevento(rs.getString(4));
                    eventos.setComienzo(rs.getString(5));
                    eventos.setFin(rs.getString(6));
                    eventos.setDetalle(rs.getString(7));

                } else {
                    message = "Registro no encontrado";
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return eventos;
    }

    @Override
    public String eventosUpd(Eventos eventos) {
        sql.delete(0, sql.length())
                .append("UPDATE eventos SET ")
                .append("idtipoevento = ?,")
                .append("evento = ?,")
                .append("descripcionevento = ?,")
                .append("comienzo = ?,")
                .append("fin = ?,")
                .append("detalle = ? ")
                .append("WHERE idevento = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, eventos.getIdtipoevento());
            ps.setString(2, eventos.getEvento());
            ps.setString(3, eventos.getDescripcionevento());
            ps.setString(4, eventos.getComienzo());
            ps.setString(5, eventos.getFin());
            ps.setString(6, eventos.getDetalle());
            ps.setInt(7, eventos.getIdevento());

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
    public String getMessage() {
        return message;
    }

}
