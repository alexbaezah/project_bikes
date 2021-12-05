
package com.duoc.controllers;

import com.duoc.conexion.Conexion;
import com.duoc.models.Categoria;
import com.duoc.models.CustomException;
import com.duoc.models.Fabricante;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class CategoriaController implements iConexion<Categoria> {
    
    private final static String SQL_INSERT = "INSERT INTO CATEGORIA (ID, DESCRIPCION) VALUES (?,?)";
    private final static String SQL_UPDATE = "UPDATE CATEGORIA SET DESCRIPCION = ? WHERE ID = ?";
    private final static String SQL_DELETE = "DELETE FROM CATEGORIA WHERE ID = ?";
    private final static String SQL_READ = "SELECT * FROM CATEGORIA WHERE ID = ?";
    private final static String SQL_READALL = "SELECT * FROM CATEGORIA ORDER BY DESCRIPCION ASC";
    private final static String SQL_MAX_ID = "SELECT MAX(ID) FROM CATEGORIA";
    
    // Definir un objeto controlador para enlazar este controladoro con la BDD
    private static final Conexion CONEXION = Conexion.obtenerConexion();
    
    // Definir un objeto PreparedStatement para ejecutar las consultas
    private PreparedStatement ps;
    
    // Definir un objeto ResultSet para almacenar en memoria el resultado
    // de la consulta realizada
    
    private ResultSet rs;

    @Override
    public boolean create(Categoria t) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getID());
            ps.setString(2, t.getDescripcion());
            if(ps.executeUpdate() > 0)
            {
                return true;
            }
            return false;
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
    public boolean update(Categoria t) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE);
            ps.setString(1, t.getDescripcion());
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
    public Categoria read(Object key) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, (int) key);
            rs = ps.executeQuery();
            Categoria c = null;
            if(rs.next())
            {
                c = new Categoria(rs.getInt(1), rs.getString(2));
            
            }
            return c;
        
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
    public ArrayList<Categoria> readAll() {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_READALL);
            rs = ps.executeQuery();
            ArrayList<Categoria> categorias = new ArrayList<>();
            while(rs.next())
            {
                categorias.add(new Categoria(rs.getInt(1), rs.getString(2)));
            }
            return categorias;
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
