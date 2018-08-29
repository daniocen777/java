package dao.impl;

import dao.Dao;
import dao.DaoActivo;
import dto.Activo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoActivoImpl extends Dao implements DaoActivo {

    @Override
    public List<Activo> activosQry() {
        List<Activo> list = null;
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
                Activo activo = new Activo();

                activo.setIdactivo(rs.getInt(1));
                activo.setNombactivo(rs.getString(2));
                activo.setTipo(rs.getString(3));

                list.add(activo);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String activosIns(Activo activo) {
        sql.delete(0, sql.length())
                .append("INSERT INTO activo(")
                .append("nombactivo,")
                .append("tipo")
                .append(") VALUES(?,?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setString(1, activo.getNombactivo().toUpperCase());
            ps.setString(2, activo.getTipo().toUpperCase());

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? null : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String activosDel(Activo activo) {
        sql.delete(0, sql.length())
                .append("DELETE FROM activo WHERE idactivo = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, activo.getIdactivo());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                ok = false;
                message = "ID " + activo.getIdactivo() + " no encontrado";
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
    public String activosUpd(Activo activo) {
        sql.delete(0, sql.length())
                .append("UPDATE activo SET ")
                .append("nombactivo = ?,")
                .append("tipo = ? ")
                .append("WHERE idactivo = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Antes de ejecutar, enviar los parámetros
            ps.setString(1, activo.getNombactivo().toUpperCase());
            ps.setString(2, activo.getTipo().toUpperCase());
            ps.setInt(3, activo.getIdactivo());

            int ctos = ps.executeUpdate(); // Retorna entero
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String activoDelMult(List<Integer> ids) {
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
            message = e.getMessage() + " -- " + "ERROR DE LLAVE FORÁNEA" + " -- ";
        }
        return message;
    }

    @Override
    public String activosInsMult(List<Activo> liataActivos) {
        sql.delete(0, sql.length())
                .append("INSERT INTO activo(")
                .append("nombactivo,")
                .append("tipo")
                .append(") VALUES(?,?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false); // No afectar a la BD
            // 3 ==> Flag para saber si es correcto
            boolean ok = true;
            for (Activo act : liataActivos) {
                ps.setString(1, act.getNombactivo().toUpperCase()); // Parámetro nombre de activo
                ps.setString(2, act.getTipo().toUpperCase()); // Parámetro nombre de activo
                int ctos = ps.executeUpdate(); // Ejecutar
                if (ctos == 0) {
                    ok = false;
                    message = "Error al insertar activo " + act.getNombactivo();
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
    public String activoXId(Integer id) {
        String activo = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("nombactivo ")
                .append("FROM activo ")
                .append("WHERE idactivo = ?");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    activo = rs.getString(1);
                } else {
                    message = "Rsgistro no encontrado";
                }
            } catch (Exception e) {
                message = e.getMessage();
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return activo;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
