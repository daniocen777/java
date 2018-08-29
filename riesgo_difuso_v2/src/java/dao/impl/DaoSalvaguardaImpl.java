package dao.impl;

import dao.Dao;
import dao.DaoSalvaguarda;
import dto.ActEspec;
import dto.Amenaza;
import dto.Responsable;
import dto.Salvaguarda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DaoSalvaguardaImpl extends Dao implements DaoSalvaguarda {
    
    @Override
    public List<Salvaguarda> salvaguardasQry() {
        List<Salvaguarda> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("salvaguarda.idsalvaguarda,") // 1
                .append("actespec.idactespec,") // 2
                .append("actespec.nombactespec,") // 3
                .append("amenaza.idamenaza,") // 4
                .append("amenaza.amenaza,") // 5
                .append("amenaza.consecuencia,") // 6
                .append("responsable.idresponsable,") // 7
                .append("responsable.nombre,") // 8
                .append("salvaguarda.control ") // 9
                .append("FROM salvaguarda ")
                .append("INNER JOIN actespec ON salvaguarda.actespec_idactespec = actespec.idactespec ")
                .append("INNER JOIN amenaza ON salvaguarda.amenaza_idamenaza = amenaza.idamenaza ")
                .append("INNER JOIN responsable ON salvaguarda.responsable_idresponsable = responsable.idresponsable");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());
                ResultSet rs = ps.executeQuery()) {
            
            list = new LinkedList<>();
            
            while (rs.next()) {
                Salvaguarda salvaguarda = new Salvaguarda();
                ActEspec actEspec = new ActEspec();
                Amenaza amenaza = new Amenaza();
                Responsable responsable = new Responsable();

                // Activo específico
                actEspec.setIdactespec(rs.getInt(2));
                actEspec.setNombactespec(rs.getString(3));
                // Amenaza
                amenaza.setIdamenaza(rs.getInt(4));
                amenaza.setAmenaza(rs.getString(5));
                amenaza.setConsecuencia(rs.getString(6));
                // Responsable
                responsable.setIdresponsable(rs.getInt(7));
                responsable.setNombre(rs.getString(8));
                // Salvaguarda
                salvaguarda.setIdsalvaguarda(rs.getInt(1));
                salvaguarda.setControl(rs.getString(9));
                salvaguarda.setActEspec(actEspec);
                salvaguarda.setAmenaza(amenaza);
                salvaguarda.setResponsable(responsable);
                
                list.add(salvaguarda);
            }
            
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return list;
    }
    
    @Override
    public List<Salvaguarda> salvaguardasQryXActEspec(Integer idActivo, Integer idActEspec) {
        List<Salvaguarda> list = null;
        sql.delete(0, sql.length())
                .append("SELECT ")
                .append("salvaguarda.idsalvaguarda,") // 
                .append("amenaza.idamenaza,") // 
                .append("amenaza.amenaza ") //                
                .append("FROM salvaguarda ")
                .append("INNER JOIN actespec ON salvaguarda.actespec_idactespec = actespec.idactespec ")
                .append("INNER JOIN activo ON actespec.activo_idactivo = activo.idactivo ")
                .append("INNER JOIN amenaza ON salvaguarda.amenaza_idamenaza = amenaza.idamenaza ")
                .append("INNER JOIN responsable ON salvaguarda.responsable_idresponsable = responsable.idresponsable ")
                .append("WHERE activo.idactivo = ? ")
                .append("AND actespec.idactespec = ?");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString());) {
            
            ps.setInt(1, idActivo);
            ps.setInt(2, idActEspec);
            ResultSet rs = ps.executeQuery();
            
            list = new LinkedList<>();
            
            while (rs.next()) {
                Salvaguarda salvaguarda = new Salvaguarda();
                Amenaza amenaza = new Amenaza();

                // Amenaza
                amenaza.setIdamenaza(rs.getInt(2));
                amenaza.setAmenaza(rs.getString(3));
                // Salvaguarda
                salvaguarda.setIdsalvaguarda(rs.getInt(1));
                salvaguarda.setAmenaza(amenaza);
                
                list.add(salvaguarda);
            }
            
        } catch (SQLException e) {
            message = e.getMessage();
        }
        
        return list;
    }
    
    @Override
    public String salvaguardasIns(Salvaguarda salvaguarda) {
        sql.delete(0, sql.length())
                .append("INSERT INTO salvaguarda(")
                .append("actespec_idactespec,")
                .append("amenaza_idamenaza,")
                .append("responsable_idresponsable,")
                .append("control")
                .append(") VALUES(?, ?, ?, ?)");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            
            ps.setInt(1, salvaguarda.getActEspec().getIdactespec());
            ps.setInt(2, salvaguarda.getAmenaza().getIdamenaza());
            ps.setInt(3, salvaguarda.getResponsable().getIdresponsable());
            ps.setString(4, salvaguarda.getControl().toUpperCase());
            
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
    public String salvaguardasDel(Salvaguarda salvaguarda) {
        sql.delete(0, sql.length())
                .append("DELETE FROM salvaguarda WHERE idsalvaguarda = ?");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            
            cn.setAutoCommit(false);
            boolean ok = true;
            
            ps.setInt(1, salvaguarda.getIdsalvaguarda());
            
            int ctos = ps.executeUpdate();
            
            if (ctos == 0) {
                ok = false;
                message = "ID " + salvaguarda.getIdsalvaguarda() + " no encontrado";
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
    public String salvaguardasUpd(Salvaguarda salvaguarda) {
        sql.delete(0, sql.length())
                .append("UPDATE salvaguarda SET ")
                .append("actespec_idactespec = ?,")
                .append("amenaza_idamenaza = ?, ")
                .append("responsable_idresponsable = ?,")
                .append("control = ? ")
                .append("WHERE idsalvaguarda = ?");
        
        try (Connection cn = db.getConnection();
                PreparedStatement ps = cn.prepareStatement(sql.toString())) {
            
            ps.setInt(1, salvaguarda.getActEspec().getIdactespec());
            ps.setInt(2, salvaguarda.getAmenaza().getIdamenaza());
            ps.setInt(3, salvaguarda.getResponsable().getIdresponsable());
            ps.setString(4, salvaguarda.getControl().toUpperCase());
            ps.setInt(5, salvaguarda.getIdsalvaguarda());
            
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
    public String salvaguardaDelMult(List<Integer> ids) {
        sql.delete(0, sql.length())
                .append("DELETE FROM salvaguarda WHERE idsalvaguarda = ?");
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
