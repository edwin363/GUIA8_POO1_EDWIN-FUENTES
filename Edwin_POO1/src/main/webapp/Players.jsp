<%-- 
    Document   : Jugadores
    Created on : 03-17-2018, 05:30:31 PM
    Author     : Edwin
--%>

<%@page import="com.udb.sv.controlador.PlayerCtrl"%>
<%@page import="com.udb.sv.controlador.TeamCtrl"%>
<%@page import="com.udb.sv.modelos.Team"%>
<%@page import="com.udb.sv.modelos.Player"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento de Jugadores</title>
        <link rel='stylesheet' href='webjars/bootstrap/3.2.0/css/bootstrap.min.css'>
        <script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>
        <script type="text/javascript" src="webjars/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    </head>
    <body>
        <%
            boolean estaModi = Boolean.parseBoolean((String) request.getAttribute("estaModi"));
            String nombBton = estaModi ? "Nuevo" : "Guardar"; // Para el texto del botón
            String clssEditBton = estaModi ? "" : "display: none"; //Pra ocultar botones
        %>
          <nav class="navbar navbar-default">
        <div class="container-fluid">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>

            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a  href="http://localhost:8080/GUIA8_MOISES-LEONOR/">Equipos</a>
                    </li>
                    <li >
                        <a  href="hhttp://localhost:8080/GUIA8_MOISES-LEONOR/Players.jsp">Jugadores</a>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
        <div class="container">
            <br>
            <div class="alert alert-success">
                <strong>Indicaciones:</strong> Usando Bootstrap con la Guía 08. 
            </div>
            <div class="row">
                <div class="col-md-5">
                    <div class="panel panel-primary">
                        <div class="panel-heading">El Formulario</div>
                        <div class="panel-body">
                            <div class="alert alert-success" >
                                ${mensAler}
                            </div>
                            <form method="POST" action="PlayersServ" name="Demo">
                                <input type="hidden" name="codi" id="codi" value="${codi}"/>
                                <div class="form-group">
                                    <label for="nomb">Nombre:</label>
                                    <input type="text" class="form-control" name="nomb" id="nomb" value="${nomb}"/>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Edad:</label>
                                    <input type="text" class="form-control" name="edad" id="edad" value="${edad}"/>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Altura:</label>
                                    <input type="text" class="form-control" name="altura" id="altura" value="${altura}"/>
                                </div>
                                <div class="form-group">
                                    <label for="desc">Peso:</label>
                                    <input type="text" class="form-control" name="peso" id="peso" value="${peso}"/>
                                </div>
                                <div class="form-group">
                                    <label for="select">Equipos</label>
                                    <select class="form-control" id="select" name="equipo" value="${codigoE}">
                                        <%
                                            for (Team tem : new TeamCtrl().getTeams()) {
                                        %>
                                        <option  value="<%= tem.getCodigo()%>"><%= tem.getName()%> </option>
                                        %>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>

                                <input type="submit" class="btn btn-default" name="btonEqui" value="<%=nombBton%>"/>
                                <input type="submit" class="btn btn-primary" style="<%=clssEditBton%>" name="btonEqui" value="Modificar"/>
                                <input type="submit" class="btn btn-danger" style="<%=clssEditBton%>" name="btonEqui" value="Eliminar"/>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="col-md-7">
                    <div class="panel panel-primary">
                        <div class="panel-heading">La Tabla</div>
                        <div class="panel-body">
                            <form method="POST" action="PlayersServ" name="Tabl">
                                <table class="table table-bordered">
                                    <tr>
                                        <th>Cons</th>
                                        <th>Nombre</th>
                                        <th>Edad</th>
                                        <th>Altura</th>
                                        <th>Peso</th>
                                        <th>Equipo</th>
                                    </tr>
                                    <%
                                        for (Player temp : new PlayerCtrl().getPlayers()) {
                                    %>
                                    <tr>
                                        <td><input type="radio" name="codiJuga" value="<%= temp.getCodigo()%>"/></td>
                                        <td><%= temp.getName()%></td>
                                        <td><%= temp.getAge()%></td>
                                        <td><%= temp.getHeight()%></td>
                                        <td><%= temp.getWeight()%></td>
                                        <td><%= temp.getCodigoEquipo().getName()%></td>
                                    </tr>
                                    <%
                                        }
                                    %>
                                </table>
                                <input type="submit" class="btn btn-success" name="btonEqui" value="Consultar"/>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        var ops = document.querySelectorAll('option');
        for (var i = 0; i < ops.length; i++) {
            if (ops[i].value === "${codigoE}") {
                ops[i].selected = true;
                break;
            }


        }

    </script>
</html>

