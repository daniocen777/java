/*
 Implementación de la interface DaoActivo
 */
package dao.impl;

import dao.DaoActivo;
import dto.Activo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author DANIEL
 */
public class DaoActivoImpl extends Dao implements DaoActivo {

    @Override
    public List<Object[]> activoQry() {
        List<Object[]> list = null;
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

            list = new LinkedList<>(); // Instanciar la lista
            while (rs.next()) {
                Object[] fil = new Object[3];

                //Activo activo = new Activo();
                fil[0] = rs.getInt(1);
                //activo.setIdactivo(rs.getInt(1));
                fil[1] = rs.getString(2);
                //activo.setNombact(rs.getString(2));
                fil[2] = rs.getString(3);
                //activo.setTipoact(rs.getString(3));
                //fil[3] = activo.getPrueba();

                list.add(fil);
            }

        } catch (SQLException e) {
            message = e.getMessage();
        }
        return list;
    }

    @Override
    public List<Object[]> activoPorNombreQry(String nombact) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String activoIns(Activo activo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String activoDel(List<Integer> ids) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Activo activoGet(Integer idactivo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String activoUpd(Activo activo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Object[]> activoCbo() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMessage() {
        return message;
    }

}
