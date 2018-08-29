package dao.impl;

import dao.Dao;
import dao.DaoCatDeRiesgo;
import dto.CatDeRiesgo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoCatDeRiesgoImpl extends Dao implements DaoCatDeRiesgo {

    @Override
    public List<CatDeRiesgo> catDeRiesgoQry() {
        List<CatDeRiesgo> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("`idcatderiesgo`,")
                .append("`categoria`,")
                .append("`desc` ")
                .append("FROM catderiesgo ")
                .append("ORDER BY `categoria`");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                CatDeRiesgo catDeRiesgo = new CatDeRiesgo();

                catDeRiesgo.setIdcatderiesgo(rs.getInt(1));
                catDeRiesgo.setCategoria(rs.getString(2));
                catDeRiesgo.setDesc(rs.getString(3));

                list.add(catDeRiesgo);
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
