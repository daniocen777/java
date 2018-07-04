package dao.impl;

import dao.DaoAutores;
import dto.Autores;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoAutoresImpl extends Dao implements DaoAutores {

    @Override
    public List<Object[]> autoresQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,") // 
                .append("autor ")
                .append("FROM autores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery();) {

            list = new LinkedList<>();

            while (rs.next()) {
                Object[] fil = new Object[2];

                //Activo activo = new Activo();
                fil[0] = rs.getInt(1);
                //activo.setIdactivo(rs.getInt(1));
                fil[1] = rs.getString(2);

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public List<Autores> autoresQry2() {
        List<Autores> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,") // 
                .append("autor ")
                .append("FROM autores ")
                .append("ORDER BY autor");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Autores autores = new Autores();

                autores.setIdautor(rs.getInt(1));
                autores.setAutor(rs.getString(2));
                autores.setValor(autores.valorCalculado());

                list.add(autores);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String autoresIns(Autores autores) {
        sql.delete(0, sql.length())
                .append("INSERT INTO autores(")
                .append("autor")
                .append(") VALUES(?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, autores.getAutor().toUpperCase());

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
    public String autoresDel(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM autores WHERE idautor = ?");

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
    public String autoresDel2(Autores autores) {
        sql.delete(0, sql.length())
                .append("DELETE FROM autores WHERE idautor = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, autores.getIdautor());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                ok = false;
                message = "ID " + autores.getIdautor() + " no encontrado";
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
    public Autores autoresGet(Integer idautor) {
        Autores autores = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,")
                .append("autor ")
                .append("FROM autores ")
                .append("WHERE idautor = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, idautor);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    autores = new Autores();

                    autores.setIdautor(rs.getInt(1));
                    autores.setAutor(rs.getString(2));

                } else {
                    message = "Registro no encontrado";
                }

            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return autores;
    }

    @Override
    public String autoresUpd(Autores autores) {
        sql.delete(0, sql.length())
                .append("UPDATE autores SET ")
                .append("autor = ? ")
                .append("WHERE idautor = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, autores.getAutor().toUpperCase());
            ps.setInt(2, autores.getIdautor());

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
    public List<Object[]> autoresCbo() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,") // 
                .append("autor ")
                .append("FROM autores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

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
    public String getMessage() {
        return message;
    }

    @Override
    public List<Object[]> autoresQry2(Integer numPag, Integer filsXpag) {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,") // 
                .append("autor ")
                .append("FROM autores ")
                .append("LIMIT ?, ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {

            ps.setInt(1, numPag * filsXpag);
            ps.setInt(2, filsXpag);

            try (ResultSet rs = ps.executeQuery();) {
                list = new LinkedList<>();

                while (rs.next()) {
                    Object[] fil = new Object[2];

                    fil[0] = rs.getInt(1);
                    fil[1] = rs.getString(2);

                    list.add(fil);
                }
            } catch (SQLException e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    /**
     *
     * @param filsXpag
     * @return
     */
    @Override
    public Integer autoresPags(Integer filsXpag) {
        Integer ctasPag = null;
        sql.delete(0, sql.length())
                .append("SELECT COUNT(*) FROM departamentos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                Integer fils = rs.getInt(1);

                ctasPag = fils % filsXpag == 0
                        ? fils / filsXpag
                        : fils / filsXpag + 1;
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return ctasPag;
    }

    @Override
    public List<Autores> autoresCbo2() {
        List<Autores> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idautor,") // 
                .append("autor ")
                .append("FROM autores");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Autores autores = new Autores();

                autores.setIdautor(rs.getInt(1));
                autores.setAutor(rs.getString(2));

                list.add(autores);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

}
