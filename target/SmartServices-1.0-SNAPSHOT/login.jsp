<%
    if(session.getAttribute("usuario") !=null){
        response.sendRedirect(("index.jsp"));
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
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
                            <a class="link" href="https://www,github.com/orlandogonz">Portafolio</a>
                        </li>
                        <li>
                            <a class="link" href="https://www.linkedin.com/in/orlandogonz">Experiencia</a>
                        </li>
                        <li>
                            <a class="link" href="#contacto">Trabajemos juntos</a>
                        </li>
                    </ol>
                </nav>
            </div>
        </header>
        
        <div>
            <form method="post" action="procesar">
                <div class="container">
                    <h1>Smart Services</h1>
                    <h2>Ingrese al sistema</h2>
                    
                    <div class="row">
                        <div class="col-md-8">
                            <label>Username:</label>
                            <input type="text" class="form-control" name="user" id="user">                    
                        </div>
                    </div>    
                    <div class="row">
                        <div class="col-md-8">
                            <label>Password:</label>
                            <input type="password" class="form-control" name="pass" id="pass">                        
                        </div>
                    </div>
                    <hr>
                    <button type="submit" class="btn btn-primary">Enviar</button> 
                </div>               
            </form>
            
            <%if(session.getAttribute("error") != null){%>
            
                <div class="alert alert-danger" role="alert">
                    <%= session.getAttribute("error") %>                
                </div>
            <%}%>    
            
        </div>
    </body>
</html>
