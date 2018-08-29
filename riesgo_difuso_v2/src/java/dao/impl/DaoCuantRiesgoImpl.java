package dao.impl;

import dao.Dao;
import dao.DaoCuantRiesgo;
import dto.ActEspec;
import dto.Activo;
import dto.Amenaza;
import dto.CuantRiesgo;
import dto.Salvaguarda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.time.LocalDate;

import java.util.LinkedList;
import java.util.List;

import parainfo.convert.DeString;

public class DaoCuantRiesgoImpl extends Dao implements DaoCuantRiesgo {

    @Override
    public List<CuantRiesgo> cuantRiesgosQry() {
        List<CuantRiesgo> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("cuantriesgo.idcuantriesgo,") // 1
                .append("salvaguarda.idsalvaguarda,") // 2
                .append("activo.nombactivo,") // 3
                .append("actespec.nombactespec,") // 4
                .append("amenaza.amenaza,") // 5
                .append("cuantriesgo.valoramenaza,") // 6
                .append("cuantriesgo.valorimpacto,") // 7
                .append("cuantriesgo.valorvulner,") // 8
                .append("cuantriesgo.fecreg ") // 9
                .append("FROM cuantriesgo ")
                .append("INNER JOIN salvaguarda ON cuantriesgo.salvaguarda_idsalvaguarda = salvaguarda.idsalvaguarda ")
                .append("INNER JOIN actespec ON salvaguarda.actespec_idactespec = actespec.idactespec ")
                .append("INNER JOIN activo ON actespec.activo_idactivo = activo.idactivo ")
                .append("INNER JOIN amenaza ON salvaguarda.amenaza_idamenaza = amenaza.idamenaza ")
                .append("INNER JOIN responsable ON salvaguarda.responsable_idresponsable = responsable.idresponsable ")
                .append("ORDER BY activo.nombactivo");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                CuantRiesgo cuantRiesgo = new CuantRiesgo();
                Salvaguarda salvaguarda = new Salvaguarda();
                Activo activo = new Activo();
                ActEspec actEspec = new ActEspec();
                Amenaza amenaza = new Amenaza();

                cuantRiesgo.setIdcuantriesgo(rs.getInt(1));
                salvaguarda.setIdsalvaguarda(rs.getInt(2));
                activo.setNombactivo(rs.getString(3));
                actEspec.setNombactespec(rs.getString(4));
                amenaza.setAmenaza(rs.getString(5));
                cuantRiesgo.setValoramenaza(rs.getString(6));
                cuantRiesgo.setValorimpacto(rs.getString(7));
                cuantRiesgo.setValorvulner(rs.getString(8));
                cuantRiesgo.setFecreg(rs.getDate(9));
                cuantRiesgo.setDefu(cuantRiesgo.defuzzificar());
                cuantRiesgo.setRie(cuantRiesgo.calcularRiesgo());

                actEspec.setActivo(activo);
                salvaguarda.setActEspec(actEspec);
                salvaguarda.setAmenaza(amenaza);
                cuantRiesgo.setSalvaguarda_idsalvaguarda(salvaguarda);

                list.add(cuantRiesgo);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String cuantRiesgosIns(CuantRiesgo cuantRiesgo) {
        sql.delete(0, sql.length())
                .append("INSERT INTO cuantriesgo(")
                .append("salvaguarda_idsalvaguarda,")
                .append("valoramenaza,")
                .append("valorimpacto,")
                .append("valorvulner,")
                .append("fecreg")
                .append(") VALUES(?, ?, ?, ?, ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setInt(1, cuantRiesgo.getSalvaguarda_idsalvaguarda().getIdsalvaguarda());
            ps.setString(2, cuantRiesgo.getValoramenaza());
            ps.setString(3, cuantRiesgo.getValorimpacto());
            ps.setString(4, cuantRiesgo.getValorvulner());
            LocalDate today = LocalDate.now();
            Integer ano = today.getYear();
            Integer mes = today.getMonthValue();
            Integer dia = today.getDayOfMonth();
            String fecha = dia + "-" + mes + "-" + ano;
            Date laFecha = DeString.aDate(fecha);
            ps.setDate(5, laFecha);

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
    public String cuantRiesgosDel(CuantRiesgo cuantRiesgo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cuantRiesgosUpd(CuantRiesgo cuantRiesgo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String cuantRiesgoDelMult(List<Integer> ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        return message;
    }

}
