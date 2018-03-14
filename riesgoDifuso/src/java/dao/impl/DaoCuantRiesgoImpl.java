package dao.impl;

import dao.DaoCuantRiesgo;
import dto.CuantRiesgo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import parainfo.sql.ConectaDb;

public class DaoCuantRiesgoImpl implements DaoCuantRiesgo {

    // Cabecera
    private ConectaDb db;
    private StringBuilder sql;
    private String message;

    public DaoCuantRiesgoImpl() {
        this.db = new ConectaDb();
        this.sql = new StringBuilder();
    }

    @Override
    public List<Object[]> cuantRiesgoQry() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("cuantriesgo.idcuantriesgo,")
                .append("activo.nombact,")
                .append("actespec.nombactespec,")
                .append("amenaza.nombamen,")
                .append("cuantriesgo.amenaza,")
                .append("cuantriesgo.vulnerabilidad,")
                .append("cuantriesgo.impacto ")
                .append("FROM cuantriesgo ")
                .append("INNER JOIN actespecamen ON cuantriesgo.idactespecamen = actespecamen.idactespecamen ")
                .append("INNER JOIN amenaza ON actespecamen.idamenaza = amenaza.idamenaza ")
                .append("INNER JOIN actespec ON actespecamen.idactespec = actespec.idactespec ")
                .append("INNER JOIN activo ON actespec.idactivo = activo.idactivo ")
                .append("ORDER BY activo.nombact");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {
            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                CuantRiesgo cuantRiesgo = new CuantRiesgo(); // Objeto CuantRiesgo
                Object[] fil = new Object[9]; // Arreglo de objetos
                fil[0] = rs.getInt(1); // ID cuantRiesgo
                fil[1] = rs.getString(2); // Activo
                fil[2] = rs.getString(3); // Activo específico
                fil[3] = rs.getString(4); // AMenaza
                fil[4] = rs.getString(5); // Valor de amenaza
                fil[5] = rs.getString(6); // Valor de vulnerabilidad
                fil[6] = rs.getString(7); // Valor de impacto
                cuantRiesgo.setAmenaza(rs.getString(5)); // Setear amenaza
                cuantRiesgo.setVulnerabilidad(rs.getString(6)); // Setear vulnerabilidad
                cuantRiesgo.setImpacto(rs.getString(7)); // Setear amenaza
                fil[7] = cuantRiesgo.defuzzificar(); // Calcular valor defuzi
                fil[8] = cuantRiesgo.calcularRiesgo(); // Riesgo

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public List<Object[]> cuantRiesgoGraph() {
        List<Object[]> list = null;
        sql.delete(0, sql.length())
                .append("SELECT amenaza, vulnerabilidad, impacto ")
                .append("FROM cuantriesgo");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Object[] fil = new Object[5];
                CuantRiesgo cuantRiesgo = new CuantRiesgo(); // Objeto CuantRiesgo

                fil[0] = rs.getString(1); // Amenaza
                fil[1] = rs.getString(2); // Vulnerabilidad
                fil[2] = rs.getString(3); // Impacto

                cuantRiesgo.setAmenaza(rs.getString(1)); // Setear amenaza
                cuantRiesgo.setVulnerabilidad(rs.getString(2));
                cuantRiesgo.setImpacto(rs.getString(3));

                fil[3] = cuantRiesgo.defuzzificar(); // Calcular valor defuzi
                fil[4] = cuantRiesgo.calcularRiesgo(); // Riesgo

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String cuantRiesgoIns(CuantRiesgo cuantRiesgo) {
        sql.delete(0, sql.length())
                .append("INSERT INTO cuantriesgo(")
                .append("idactespecamen,")
                .append("amenaza,")
                .append("vulnerabilidad,")
                .append("impacto ")
                .append(") VALUES(?, ?, ?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, cuantRiesgo.getIdactespecamen());
            ps.setString(2, cuantRiesgo.getAmenaza());
            ps.setString(3, cuantRiesgo.getVulnerabilidad());
            ps.setString(4, cuantRiesgo.getImpacto());

            int ctos = ps.executeUpdate();
            message = (ctos != 0) ? "ok" : "0 filas afectadas";

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
