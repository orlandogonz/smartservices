
package ar.com.smartservices.servlet;

import ar.com.smartservices.entities.Producto;
import ar.com.smartservices.repositories.ProductoRepository;
import com.google.gson.Gson;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 *
 * @author orlando [orlandogonz.dev@gmail.com]
 */

@WebServlet(name="ps", urlPatterns="/action/*")
public class Controller extends HttpServlet {
    
    private DataSource datasource;
    
    @Override
    public final void init() throws ServletException{
        
        try {
            datasource = createDataSource();
        } catch (PropertyVetoException e) {
            throw new RuntimeException("error base de datos", e);
        }
        
    }
   
    /*
        doGet
    El método de servicio servlet llama a este método para gestionar la
    solicitud HTTP GET del cliente. El método Get se usa para obtener información
    del servidor
    */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductoRepository pr = null;
        
        try {
            
            pr = new ProductoRepository(datasource.getConnection());
            
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        
        if(request.getParameter("id") == null){
            
            List<Producto> productos = pr.getAll();
            request.getSession().setAttribute("prods", productos);
            response.sendRedirect("/SmartServices/index.jsp");
            
        } else {
            
            Long id = Long.parseLong(request.getParameter("id"));
            Producto producto = pr.getById(id);
            String json = new Gson().toJson(producto);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(json);

            
            
        }
        
    }

    
    /*
    doPost: Se utiliza para publicar información en el servidor.
    este método envía archivos al servidor
    */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        ProductoRepository pr = null;
        
        try {
            pr = new ProductoRepository(datasource.getConnection());
        } catch (SQLException e) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, e);
        }
        
        List<String> mensajes = new ArrayList<String>();
        
        String action = action(request); 
        
        if (action.equals("delete")) {
            
            Long id = Long.parseLong(request.getParameter("id"));
            pr.delete(id);
            mensajes.add("Borrado con éxito el id " + id);
            
        } else if( action.equals("save")){
            
            String presentacion = request.getParameter("presentacion");
            String descripcion = request.getParameter("descripcion");
            
            Float precio = 0.0f;
            
            try {
                
                precio = precio.parseFloat(request.getParameter("precio"));
                
            } catch (NumberFormatException e) {
                mensajes.add("El precio no es numerico");
            }
            
            Integer cantidad = 0;
            
            try {
                
                cantidad = Integer.parseInt(request.getParameter("cantidad"));
                
            } catch (NumberFormatException e) {
                mensajes.add("La cantidad ingresada no es numerica");
            }
            
            if(mensajes.isEmpty()){
                
                Producto p = new Producto(presentacion, cantidad, precio, descripcion);
                pr.saves(p);
                mensajes.add("Guardado exitosamente");
            }            
        } else if(action.equals("modificar")){
            
            Long id = Long.parseLong(request.getParameter("id"));
            String presentacion = request.getParameter("presentacion");
            String descripcion = request.getParameter("descripcion");
            
            Float precio = 0.0f;
            
            try {
                
                precio = precio.parseFloat(request.getParameter("precio"));
                
            } catch (NumberFormatException e) {
                mensajes.add("El precio no es numerico");
            }
            
            Integer cantidad = 0;
            
            try {
                
                cantidad = Integer.parseInt(request.getParameter("cantidad"));
                
            } catch (NumberFormatException e) {
                mensajes.add("La cantidad ingresada no es numerica");
            }
            
            if(mensajes.isEmpty()){
                
                Producto p = new Producto(id,presentacion, cantidad, precio, descripcion);
                pr.update(p);
                mensajes.add("Actualización exitosa");
            }             
        }
        
        List<Producto> productos = pr.getAll();
        
        request.getSession().setAttribute("prods", productos);
        request.getSession().setAttribute("msgs", mensajes);
        
        response.sendRedirect("/SmartServices/index.jsp");       
        
       
    }

    private ComboPooledDataSource createDataSource() throws PropertyVetoException {
        
        ComboPooledDataSource cpds = new ComboPooledDataSource();
        cpds.setDriverClass("org.gjt.mm.mysql.Driver");
        cpds.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/abm");
        cpds.setUser("root");
        cpds.setPassword("");
        cpds.setInitialPoolSize(5);
	cpds.setMinPoolSize(5);
	cpds.setAcquireIncrement(5);
	cpds.setMaxPoolSize(20);
        
        return cpds;    
    }

    public String action(HttpServletRequest request) {
        
        String [] urlParts = request.getRequestURL().toString().split("/"); //Split de una cadena lo separa en distintas posiciones de un array
        String actionUrl = urlParts[urlParts.length -1];
        
        return actionUrl;
        
    }

    

}
