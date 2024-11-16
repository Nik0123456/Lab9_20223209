<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/includes/navbar.jsp" %>

<%@ page import="com.example.lab9_base.Bean.*" %>
<%@ page import="java.util.ArrayList" %>

<%
    //Codigo java para obtener los datos enviados por el Servlet

    ArrayList<Arbitro> arbitros = (ArrayList<Arbitro>) request.getAttribute("arbitros");

    String busqueda = (String) request.getAttribute("busqueda");

    String tipo = (String) request.getAttribute("tipo");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css'/>
        <title>LAB 9</title>
    </head>
    <body>
        <div class='container'>
            <div class="row mb-5 mt-4">
                <div class="col-lg-6">
                    <h1 class=''>Lista de Árbitros</h1>
                </div>
                <div class="col-lg-6 my-auto text-lg-right">
                    <a href="<%= request.getContextPath()%>/ArbitroServlet?action=crear" class="btn btn-primary">Crear
                        Árbitro</a>
                </div>
                <form method="post" action="<%= request.getContextPath()%>/ArbitroServlet?action=buscar" class="row">
                    <div class="col-lg-3">
                        <select name="tipo" class="form-control">
                            <option value="nombre" <%= "nombre".equals(request.getParameter("tipo")) ? "selected" : "" %>>Nombre</option>
                            <option value="pais" <%= "pais".equals(request.getParameter("tipo")) ? "selected" : "" %>>País</option>
                        </select>
                    </div>
                    <div class="col-lg-5">
                        <input type="text" class="form-control" name="buscar" value="<%= request.getParameter("buscar") != null ? request.getParameter("buscar") : "" %>">
                    </div>
                    <div class="col-lg-2">
                        <button type="submit" class="btn btn-primary">Buscar</button>
                    </div>
                    <div class="col-lg-2">
                        <a href="<%= request.getContextPath()%>/ArbitroServlet" class="btn btn-danger">Limpiar
                            Búsqueda</a>
                    </div>
                </form>
            </div>
            <table class="table">
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Pais</th>
                    <th></th>
                </tr>

                <% for (Arbitro arbitro: arbitros) {%>

                <tr>
                    <td><%=arbitro.getIdArbitro()%></td>
                    <td><%=arbitro.getNombre()%></td>
                    <td><%=arbitro.getPais()%></td>
                    <td>
                        <a href="<%=request.getContextPath()%>/ArbitroServlet?action=borrar&id=<%=arbitro.getIdArbitro()%>">
                            Borrar
                        </a>
                    </td>
                </tr>

                <% } %>

            </table>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
    </body>
</html>
