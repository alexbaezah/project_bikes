
package com.duoc.controllers;


import com.duoc.conexion.Conexion;
import com.duoc.models.Fabricante;
import com.duoc.models.CustomException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class FabricanteController implements iConexion<Fabricante> {
    
    private final static String SQL_INSERT = "INSERT INTO FABRICANTE (ID, NOMBRE) VALUES (?,?)";
    private final static String SQL_UPDATE = "UPDATE FABRICANTE SET DESCRIPCION = ? WHERE ID = ?";
    private final static String SQL_DELETE = "DELETE FROM FABRICANTE WHERE ID = ?";
    private final static String SQL_READ = "SELECT * FROM FABRICANTE WHERE ID = ?";
    private final static String SQL_READALL = "SELECT * FROM FABRICANTE ORDER BY NOMBRE ASC";
    private final static String SQL_MAX_ID = "SELECT MAX(ID) FROM FABRICANTE";
    
    // Definir un objeto controlador para enlazar este controladoro con la BDD
    private static final Conexion CONEXION = Conexion.obtenerConexion();
    
    // Definir un objeto PreparedStatement para ejecutar las consultas
    private PreparedStatement ps;
    
    // Definir un objeto ResultSet para almacenar en memoria el resultado
    // de la consulta realizada
    
    private ResultSet rs;
    
    @Override
    
    public boolean create(Fabricante t){
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getID());
            ps.setString(2, t.getNombre());
            if(ps.executeUpdate() > 0)
            {
                return true;
            }
            return false;
        } 
        catch (SQLException ex) 
        {
            return false;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
    
    
    }

    @Override
    public boolean update(Fabricante t) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getNombre());
            ps.setInt(2, t.getID());
            return ps.executeUpdate() > 0;
        }
        catch(SQLException ex)
        {
            return false;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
    }

    @Override
    public boolean delete(Object key) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_DELETE);
            ps.setInt(1, (int) key);
            return ps.executeUpdate() > 0;
        }
        catch(SQLException ex)
        {
            return false;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
    }

    @Override
    public Fabricante read(Object key) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, (int) key);
            rs = ps.executeQuery();
            Fabricante f = null;
            if(rs.next())
            {
                f = new Fabricante(rs.getInt(1), rs.getString(2));
                
            }
            return f;
        }
        catch(SQLException | CustomException ex)
        {
            return null;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
        
    }

    @Override
    public ArrayList<Fabricante> readAll() {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_READALL);
            rs = ps.executeQuery();
            ArrayList<Fabricante> fabricantes = new ArrayList<>();
            while(rs.next())
            {
                fabricantes.add(new Fabricante(rs.getInt(1), rs.getString(2)));
            }
            return fabricantes;
        }
        catch(SQLException | CustomException ex)
        {
            return null;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
        
    }

    
    
}
