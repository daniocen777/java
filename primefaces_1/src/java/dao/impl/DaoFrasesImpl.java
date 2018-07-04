package dao.impl;

import dao.DaoFrases;
import dto.Autores;
import dto.Frases;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoFrasesImpl extends Dao implements DaoFrases {

    @Override
    public List<Object[]> frasesQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("frases.idfrase,")
                .append("autores.autor,")
                .append("frases.frase ")
                .append("FROM frases ")
                .append("INNER JOIN autores ")
                .append("ON frases.idautor = autores.idautor");

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
    public List<Frases> frasesQry2() {
        List<Frases> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("frases.idfrase,")
                .append("autores.idautor,")
                .append("autores.autor,")
                .append("frases.frase ")
                .append("FROM frases ")
                .append("INNER JOIN autores ")
                .append("ON frases.idautor = autores.idautor ")
                .append("ORDER BY autores.autor");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Frases frases = new Frases();
                Autores autores = new Autores();
                autores.setIdautor(rs.getInt(2));
                autores.setAutor(rs.getString(3));

                frases.setIdfrase(rs.getInt(1));
                frases.setAutores(autores);
                frases.setFrase(rs.getString(4));

                list.add(frases);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String frasesIns(Frases frases) {
        sql.delete(0, sql.length())
                .append("INSERT INTO frases(")
                .append("idautor,")
                .append("frase ")
                .append(") VALUES(?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, frases.getAutores().getIdautor());
            ps.setString(2, frases.getFrase().toUpperCase());

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
    public String frasesDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM frases WHERE idfrase = ?");

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
    public String frasesDel2(Frases frases) {
        sql.delete(0, sql.length())
                .append("DELETE FROM frases WHERE idfrase = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, frases.getIdfrase());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                ok = false;
                message = "ID " + frases.getIdfrase() + " no encontrado";
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
    public Frases frasesGet(Integer idfrase) {
        Frases frases = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idfrase,")
                .append("idautor,")
                .append("frase ")
                .append("FROM frases ")
                .append("WHERE idfrase = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idfrase);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    frases = new Frases();

                    frases.setIdfrase(rs.getInt(1));
                    //frases.setIdautor(rs.getInt(2));
                    frases.setFrase(rs.getString(3));

                } else {
                    message = "Registro no encontrado";
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return frases;
    }

    @Override
    public String frasesUpd(Frases frases) {
        sql.delete(0, sql.length())
                .append("UPDATE frases SET ")
                .append("idautor = ?,")
                .append("frase = ? ")
                .append("WHERE idfrase = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, frases.getAutores().getIdautor());
            ps.setString(2, frases.getFrase().toUpperCase());
            ps.setInt(3, frases.getIdfrase());

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
