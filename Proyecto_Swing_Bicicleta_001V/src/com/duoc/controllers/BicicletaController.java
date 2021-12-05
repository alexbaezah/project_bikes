
package com.duoc.controllers;

import com.duoc.conexion.Conexion;
import com.duoc.models.Bicicleta;
import com.duoc.models.CustomException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class BicicletaController implements iConexion<Bicicleta> {
    
    // Definir las instrucciones en SQL
    private final static String SQL_INSERT =  "INSERT INTO BICICLETA (ID, MODELO, TALLA, SUSPENSION, TRANSMISION, FRENOS, STOCK, VALOR, FABRICANTE, CATEGORIA)VALUES (?,?,?,?,?,?,?,?,?,?)";
    private final static String SQL_UPDATE = "UPDATE BICICLETA SET MODELO = ?, TALLA = ?, SUSPENSION = ?, TRANSMISION = ?, FRENOS = ?, STOCK = ?, VALOR = ?, FABRICANTE = ?, CATEGORIA = ?";
    private final static String SQL_DELETE = "DELETE FROM BICICLETA WHERE ID = ?";
    private final static String SQL_READ =  " SELECT * FROM BICICLETA WHERE ID = ?";
    private final static String SQL_READALL = "SELECT * FROM BICICLETA ORDER BY ID ASC";
    private final static String SQL_BICICLETAS_W_FABRICANTE = "SELECT B,ID, B.MODELO, B.TALLA, B.SUSPENSION, B.TRANSMISION, B.FRENOS, B.STOCK, B.VALOR FROM BICICLETA B INNER JOIN FABRICANTE F ON B.FABRICANTE = F.ID ORDER BY B.ID";
    private final static String SQL_BICICLETAS_W_CATEGORIA = "SELECT B,ID, B.MODELO, B.TALLA, B.SUSPENSION, B.TRANSMISION, B.FRENOS, B.STOCK, B.VALOR FROM BICICLETA B INNER JOIN CATEGORIA C ON B.CATEGORIA = C.ID ORDER BY B.ID";

    // Definir un objeto controlador para enlazar este controladoro con la BDD
    private static final Conexion CONEXION = Conexion.obtenerConexion();
    
    //Definir un objeto que nos permita preparar y ejecutar las consultas a la BBDD.
    private PreparedStatement ps;
    
    //Definir un objeto que nos permita almacenar en memoria los datos obtenidos al ejecutar consultas.
    private ResultSet rs;
    
    @Override
    public boolean create(Bicicleta t) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_INSERT);
            ps.setInt(1, t.getID());
            ps.setString(2, t.getModelo());
            ps.setString(3, t.getTalla());
            ps.setString(4, t.getSuspension());
            ps.setString(5, t.getTransmision());
            ps.setString(6, t.getFrenos());
            ps.setInt(7, t.getStock()); 
            ps.setInt(8, t.getValor());
            ps.setInt(9, t.getFabricante());
            ps.setInt(10, t.getCategoria());          
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
    public boolean update(Bicicleta t) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_UPDATE);
            ps.setInt(1, t.getID());
            ps.setString(2, t.getModelo());
            ps.setString(3, t.getTalla());
            ps.setString(4, t.getSuspension());
            ps.setString(5, t.getTransmision());
            ps.setString(6, t.getFrenos());
            ps.setInt(7, t.getStock()); 
            ps.setInt(8, t.getValor());
            ps.setInt(9, t.getFabricante());
            ps.setInt(10, t.getCategoria());          
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
    public Bicicleta read(Object key) {
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_READ);
            ps.setInt(1, (int) key);
            rs = ps.executeQuery();
            Bicicleta b = null;
            if(rs.next())
            {
                b = new Bicicleta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10));
                
            }
            return b;
        }
        catch(SQLException | CustomException  ex)
        {
            return null;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
        
    }

    @Override
    public ArrayList<Bicicleta> readAll() {
       try
       {
           ps = CONEXION.getConexion().prepareStatement(SQL_READALL);
           rs = ps.executeQuery();
           ArrayList<Bicicleta> bicicletas = new ArrayList<>();
           while(rs.next())
           {
               bicicletas.add(new Bicicleta(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getInt(10)));
           }
           return bicicletas;
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
    
    public ResultSet obtenerBicicletaCategoria(){
        try
        {
            ps = CONEXION.getConexion().prepareStatement(SQL_BICICLETAS_W_CATEGORIA);
            return ps.executeQuery();
        }
        catch(SQLException ex)
        {
            return null;
        }
        finally
        {
            CONEXION.cerrarConexion();
        }
    
    }
    
            
            
    
}
