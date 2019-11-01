
package ar.com.smartservices.repositories;

import ar.com.smartservices.entities.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author orlando [orlandogonz.dev@gmail.com]
 */
public class ProductoRepository {
    
    private Connection conexion;

    public ProductoRepository(Connection conexion) {
        this.conexion = conexion;
    }
    
    //Metodo que guarda los productos insertados en la base de datos
    public Integer saves(Producto p){
        
        int inserted = 0;
        
        try {
            
            PreparedStatement stmt = conexion.prepareStatement("insert into productos (presentacion, cantidad, precio, descripcion) values (?,?,?,?)");
            stmt.setString(1,p.getPresentacion());
            stmt.setInt(2, p.getCantidad());
            stmt.setFloat(3, p.getPrecio());
            stmt.setString(4, p.getDescripcion());
            inserted = stmt.executeUpdate();            
            
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando con la base de datos", e);
        }
        
        return inserted;
        
    }
    
    //Metodo que actualiza productos en la base de datos
    public Integer update(Producto p){
        
        int inserted = 0;
        
        try {
            
            PreparedStatement preparedstatement = conexion.prepareStatement("update productos set presentacion=?, cantidad=?, precio=?, descripcion=?");
            
            preparedstatement.setString(1, p.getPresentacion());
            preparedstatement.setInt(2,p.getCantidad());
            preparedstatement.setFloat(3, p.getPrecio());
            preparedstatement.setString(4, p.getDescripcion());
            inserted = preparedstatement.executeUpdate();
            
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando con la base de datos", e); 
        }
        
        return inserted;
    }
    
    //Metodo que borra el id de la base de datos
    public Integer delete(Long id){
        
        int inserted = 0;
        
        try {
            
            PreparedStatement preparedStatement = conexion.prepareStatement("delete from productos where id=?");
            
            preparedStatement.setLong(1, id);
            inserted = preparedStatement.executeUpdate();            
            
        } catch (SQLException e) {
            throw new RuntimeException("Error consultando con la base de datos", e);
        }
        
        return inserted;
    }
    
    
    public Producto getById(long id){
        
        Producto producto = null;
        
        try {
            
            PreparedStatement preparedStatement = conexion.prepareStatement("select * from productos where id=?");            
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            
            if (rs.next()) {
                
                String presentacion = rs.getString("presentacion");
                Integer cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio");
                String nota = rs.getString("descripcion");
                
                producto = new Producto(id,presentacion, cantidad, precio, nota);
                
            }
            
            
        } catch (SQLException e) {
            throw new RuntimeException("error consultando la base de datos", e);
        }
        
        return producto;
    }
    
    public List<Producto> getAll(){
        
        List<Producto> productos = new ArrayList<>();
        
        try {
            
            Statement statement = conexion.createStatement();
            ResultSet rs = statement.executeQuery("select * from productos");
            
            while (rs.next()) {
                
                Long id = rs.getLong("id");
                String presentacion = rs.getString("presentacion");
                Integer cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio");
                String nota = rs.getString("descripcion");
                Producto p = new Producto(id, presentacion, cantidad, precio, nota);
                productos.add(p);               
                
            }
            
        } catch (SQLException e) {
            throw new RuntimeException("error consultando la base de datos", e);
        }
        
        return productos;        
    }
    
    public List<Producto> searchByDescripcion(String descripcion){
        
        List<Producto> productos = new ArrayList<>();
        
        try {
            
            PreparedStatement preparedStatement = conexion.prepareStatement("select * from productos p where p.descripcion like ?");
            preparedStatement.setString(1,"%" + descripcion + "%");
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                
                Long id = rs.getLong("id");
                String presentacion = rs.getString("presentacion");
                Integer cantidad = rs.getInt("cantidad");
                Float precio = rs.getFloat("precio");
                String nota = rs.getString("descripcion");
                
                Producto p = new Producto(id, presentacion, cantidad, precio, nota);
                productos.add(p);                
            }            
        } catch (SQLException e) {
            throw new RuntimeException("error consultadndo la base de datos", e);
        }
        
        return productos;
    }    
}
