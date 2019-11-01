<%-- 
    Document   : index
    Created on : 28-oct-2019, 21:04:35
    Author     : orlando
--%>

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
        <div class="container">
            <h1>Smart Services</h1>
            <form>
                <div class="row">
                    <div class="form-group col-6">
                        <label>Presentación:</label>
                        <input type="text" class="form-control" name="presentacion">
                    </div>                    
                </div>
                <div class="row">
                    <div class="form-group col-6">
                        <label>Descripción:</label>
                        <input type="text" class="form-control" name="descripcion">
                    </div>                    
                </div>
                <div class="row">
                    <div class="form-group col-6">
                        <label>Precio</label>
                        <input type="text" class="form-control" name="precio">
                    </div>                    
                </div>
                <div class="row">
                    <div class="form-group col-6">
                        <label>Cantidad</label>
                        <input type="text" class="form-control" name="cantidad">
                    </div>                    
                </div>
                <button type="submit" class="btn btn-info">Enviar</button>
            </form>
            </br>
            
            <% List<String> mensajes = (List<String>) session.getAttribute("msgs");  %>
            
            
        </div>
        
    </body>
</html>
