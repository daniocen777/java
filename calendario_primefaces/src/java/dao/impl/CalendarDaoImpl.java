package dao.impl;

import dao.CalendarDao;
import dao.Dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;
import model.Calendar;

public class CalendarDaoImpl extends Dao implements CalendarDao {

    @Override
    public List<Calendar> calendarQry() {
        List<Calendar> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("id,")
                .append("fecha ")
                .append("FROM  calendario");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Calendar calendar = new Calendar();

                calendar.setId(rs.getInt(1));
                calendar.setFecha(rs.getDate(2));

                list.add(calendar);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String calendarIns(Calendar calendar) {
        // Formato de fecha
        SimpleDateFormat fechaFormat = new SimpleDateFormat("yyyy-MM-dd");
        sql.delete(0, sql.length())
                .append("INSERT INTO calendario(")
                .append("fecha")
                .append(") VALUES(?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setString(1, fechaFormat.format(calendar.getFecha()));

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? null : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

}
