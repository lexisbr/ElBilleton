<%-- 
    Document   : CrearCuenta
    Created on : 13/11/2020, 22:57:19
    Author     : lex
--%>

<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <% String codigo = request.getParameter("cliente_codigo");
       String fecha = LocalDate.now().toString();
       request.setAttribute("fecha_creacion", fecha);
    %>
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Crear Cuenta</h2>
            <hr>
            <div class="centrar">
                <form action="../CrearCuenta" method="POST" class="form-control" style="width: 500px; height: 400px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Codigo de Cliente</h1>
                        <input type="text" name="cliente_codigo" class="form-control" readonly="" required="" value="<%=codigo%>"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha actual</h1>
                        <input type="date" name="fecha" value="<%=fecha%>" readonly="" required="">
                    </div>
                    <div class="form-group">
                        <input type="number" name="monto" class="form-control" placeholder="Monto" required="" step=".01"/>
                    </div>
                    <br>
                    <input type="submit" value="Guardar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
