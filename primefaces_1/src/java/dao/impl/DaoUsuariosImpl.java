package dao.impl;

import dao.DaoUsuarios;
import dto.Usuarios;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import util.Utilitarios;

public class DaoUsuariosImpl extends Dao implements DaoUsuarios {

    @Override
    public Usuarios autentica(String usuario, String password) {
        Usuarios usuarios = null;
        sql.append("SELECT ")
                .append("idusuario,")
                .append("apellidos,")
                .append("nombres,")
                .append("usuario,")
                .append("autorizacion ")
                .append("FROM usuarios ")
                .append("WHERE (usuario = ?) ")
                .append("AND (AES_DECRYPT(password, 'parainfo') = ?)");

        try (Connection cn = db.getConnection();
                PreparedStatement ps
                = cn.prepareStatement(sql.toString())) {

            ps.setString(1, usuario);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    usuarios = new Usuarios();

                    usuarios.setIdusuario(rs.getInt(1));
                    usuarios.setApellidos(rs.getString(2));
                    usuarios.setNombres(rs.getString(3));
                    usuarios.setUsuario(rs.getString(4));
                    usuarios.setAutorizacion(rs.getString(5));

                } else {
                    throw new SQLException("Usuario NO registrado");
                }
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return usuarios;
    }

    @Override
    public List<Usuarios> usuariosQry() {
        List<Usuarios> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("idusuario,")
                .append("apellidos,")
                .append("nombres,")
                .append("usuario,")
                .append("autorizacion ")
                .append("FROM usuarios ")
                .append("ORDER BY apellidos");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {

            list = new LinkedList<>();

            while (rs.next()) {
                Usuarios usuarios = new Usuarios();

                usuarios.setIdusuario(rs.getInt(1));
                usuarios.setApellidos(rs.getString(2));
                usuarios.setNombres(rs.getString(3));
                usuarios.setUsuario(rs.getString(4));
                usuarios.setAutorizacion(rs.getString(5));

                list.add(usuarios);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }

        return list;
    }

    @Override
    public String usuarioIns(Usuarios usuarios) {
        sql.delete(0, sql.length())
                .append("INSERT INTO usuarios(")
                .append("apellidos,")
                .append("nombres,")
                .append("usuario,")
                .append("password,")
                .append("autorizacion")
                .append(") VALUES(?,?,?,AES_ENCRYPT(?, 'parainfo'),?)");
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            // Colocar los parámetros antes de ejecutar
            ps.setString(1, usuarios.getApellidos());
            ps.setString(2, usuarios.getNombres());
            String user = Utilitarios.generarUserYPassword(usuarios.getNombres(), usuarios.getApellidos());
            ps.setString(3, user);
            ps.setString(4, user);
            ps.setString(5, usuarios.getAutorizacion());

            // Al ejecutar, retorna un entero
            int ctos = ps.executeUpdate(); // Para insert, delete y update
            message = (ctos != 0) ? "ok" : "0 filas afectadas";
        } catch (SQLException e) {
            message = e.getMessage();
        }
        return message;
    }

    @Override
    public String usuarioPassUpd(Usuarios usuarios) {
        sql.delete(0, sql.length())
                .append("UPDATE usuarios SET ")
                .append("password = AES_ENCRYPT(?, 'parainfo') ")
                .append("WHERE usuario = ?");

        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {

            ps.setString(1, usuarios.getPassword());
            ps.setString(2, usuarios.getUsuario());

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
