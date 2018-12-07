package dao.impl;

import dao.Dao;
import dao.ImagenDao;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Imagen;

public class ImagenDaoImpl extends Dao implements ImagenDao {

    @Override
    public String imagenIns(Imagen imagen) {
        sql.delete(0, sql.length())
                .append("INSERT INTO imagen(")
                .append("img")
                .append(") VALUES(?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setBinaryStream(1, imagen.getImg().getInputstream());

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? null : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        } catch (IOException ex) {
            Logger.getLogger(ImagenDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return message;
    }

}
