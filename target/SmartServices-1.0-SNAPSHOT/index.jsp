<%
    if (session.getAttribute("usuario") == null){
        session.setAttribute("error", "Primero debe loguearse");
        response.sendRedirect("login.jsp");
    } 

    Cookie [] cookies = request.getCookies(); //Me devuelve un array de cookies
    

%>

<%@page import="ar.com.smartservices.entities.Producto"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="styles/estilos.css" />
        <title>Smart Services</title>
    </head>
    <body>
        
        <header class="header">
            <div class="container">
                <figure class="logo">
                    <!--<img src="images/logo.png" height="50" alt="Logo de http://leonidasesteban.com" />-->
                </figure>
                <nav class="menu">
                    <ol>
                        <li>
                            <a class="link" href="desloguin?accion=desloguin">Finalizar Sesion</a>
                        </li>
                    </ol>
                </nav>
            </div>
        </header>
        
        
       
        
        <div class="container">
            <h1>Smart Services</h1>
            <form action="action/save" method="post">
		<div class="row">
		    <div class="form-group col-6">
			<label>Presentación:</label>
			<input type="text" class="form-control" name="presentacion" id="presentacion" />
		    </div>
		    <div class="form-group col-6">
			<label>Descripción:</label>
                        <input type="text" class="form-control" name="descripcion" id="descripcion" />
		    </div>
		</div>
		<div class="row">
		    <div class="form-group col-6">
			<label>Precio</label>
                        <input type="text" class="form-control" name="precio" id="precio" />
		    </div>
		    <div class="form-group col-6">
			<label>Cantidad</label>
			<input type="text" class="form-control" name="cantidad" id="cantidad" />
		    </div>
		</div>
		<button type="submit" class="btn btn-info">Enviar</button>
	    </form>
	    <br />
            
	    <%List<String> mensajes = (List<String>) session.getAttribute("msgs"); %>
	    <% if (mensajes != null) {%>
	    <% for (String m : mensajes) {%>
	    <div class="alert alert-danger" role="alert">
		<%=m%>
		<% session.removeAttribute("msgs"); %>
	    </div>
	    <%}%>
	    <%}%> 
	    <hr>
            
	    <%List<Producto> productos = (List<Producto>) session.getAttribute("prods");%>
            
            <table class="table table-bordered">
                
                <thead>
                    <tr>
                        <th>Id</th>
                        <th>Descripcion</th>
                        <th>Presentacion</th>
                        <th>Cantidad</th>
                        <th>Precio</th>
                        <th>Modificar</th>
                        <th>Borrar</th>
                    </tr>
                </thead>
                
                <tbody>
                    <% if(productos !=null) {%>
                    <% for(Producto p: productos) {%>
                    
                    <tr>
                        <td><%= p.getId()%></td>
                        <td><%= p.getDescripcion()%></td>
                        <td><%= p.getPresentacion()%></td>
                        <td><%= p.getCantidad()%></td>
                        <td><%= p.getPrecio()%></td>
                        <td>
                            <button type="button" class="btn btn-success" onclick="modificarRegistro(<%= p.getId()%>)" >Modificar</button>
                        </td>
                        
                        <td>
                            <form method="post" action="/SmartServices/action/delete">
                                <input type="hidden" name="id" value="<%= p.getId()%>">
				<button type="submit" class="btn btn-danger">Eliminar</button>                                
                            </form>
                        </td>
                    </tr>
                    <% } %>
		    <% session.removeAttribute("prods"); %>
		    <% }%>
                </tbody>               
            </table>
                
                <form method="post" action="action/modificar" class="hide" id="form-modificar">
                    <div class="row">
                        <div class="form-group col-6">
                            <label>Presentación:</label>
                            <input type="text" class="form-control" name="presentacion" id="mod-presentacion" />
                        </div>
                        <div class="form-group col-6">
                            <label>Descripción:</label>
                            <input type="text" class="form-control" name="descripcion" id="mod-descripcion" />
                        </div>
                    </div>
                    <div class="row">
                        <div class="form-group col-6">
                            <label>Precio</label>
                            <input type="text" class="form-control" name="precio" id="mod-precio"/>
                        </div>
                        <div class="form-group col-6">
                            <label>Cantidad</label>
                            <input type="text" class="form-control" name="cantidad" id="mod-cantidad"/>
                        </div>
                    </div>
                        <div id="ocultos"></div>
                                
                    <button type="button" class="btn btn-info btn-modificar">Modificar</button>                  
                    
                </form>    
        </div>
        <script src="js/script.js"></script>       
    </body>
</html>
