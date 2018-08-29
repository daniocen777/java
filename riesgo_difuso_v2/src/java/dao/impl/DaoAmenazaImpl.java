package dao.impl;

import dao.Dao;
import dao.DaoAmenaza;
import dto.Amenaza;
import dto.CatDeRiesgo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoAmenazaImpl extends Dao implements DaoAmenaza {

    @Override
    public List<Amenaza> amenazasQry() {
        List<Amenaza> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("amenaza.idamenaza,") // 1
                .append("catderiesgo.idcatderiesgo,") // 2
                .append("catderiesgo.categoria,") // 3
                .append("amenaza.amenaza,") // 4
                .append("amenaza.consecuencia ") // 5
                .append("FROM amenaza ")
                .append("INNER JOIN catderiesgo ")
                .append("ON amenaza.catderiesgo_idcatderiesgo = catderiesgo.idcatderiesgo ")
                .append("ORDER BY catderiesgo.categoria");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Amenaza amenaza = new Amenaza();
                CatDeRiesgo catDeRiesgo = new CatDeRiesgo();
                catDeRiesgo.setIdcatderiesgo(rs.getInt(2));
                catDeRiesgo.setCategoria(rs.getString(3));

                amenaza.setIdamenaza(rs.getInt(1));
                amenaza.setCatDeRiesgo(catDeRiesgo);
                amenaza.setAmenaza(rs.getString(4));
                amenaza.setConsecuencia(rs.getString(5));

                list.add(amenaza);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String amenazasIns(Amenaza amenaza) {
        sql.delete(0, sql.length())
                .append("INSERT INTO amenaza(")
                .append("catderiesgo_idcatderiesgo,")
                .append("amenaza, ")
                .append("consecuencia ")
                .append(") VALUES(?, ?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, amenaza.getCatDeRiesgo().getIdcatderiesgo());
            ps.setString(2, amenaza.getAmenaza().toUpperCase());
            ps.setString(3, amenaza.getConsecuencia().toUpperCase());

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
    public String amenazasDel(Amenaza amenaza) {
        sql.delete(0, sql.length())
                .append("DELETE FROM amenaza WHERE idamenaza = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            cn.setAutoCommit(false);
            boolean ok = true;

            ps.setInt(1, amenaza.getIdamenaza());

            int ctos = ps.executeUpdate();

            if (ctos == 0) {
                ok = false;
                message = "ID " + amenaza.getIdamenaza() + " no encontrado";
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
    public String amenazasUpd(Amenaza amenaza) {
        sql.delete(0, sql.length())
                .append("UPDATE amenaza SET ")
                .append("catderiesgo_idcatderiesgo = ?,")
                .append("amenaza = ?, ")
                .append("consecuencia = ? ")
                .append("WHERE idamenaza = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            // Antes de ejecutar, enviar los parámetros
            ps.setInt(1, amenaza.getCatDeRiesgo().getIdcatderiesgo());
            ps.setString(2, amenaza.getAmenaza().toUpperCase());
            ps.setString(3, amenaza.getConsecuencia().toUpperCase());
            ps.setInt(4, amenaza.getIdamenaza());

            int ctos = ps.executeUpdate(); // Retorna entero
            message = (ctos != 0) ? null : "0 filas afectadas";

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String amenazaDelMult(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM amenaza WHERE idamenaza = ?");
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
