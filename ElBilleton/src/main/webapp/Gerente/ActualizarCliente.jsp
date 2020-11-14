<%-- 
    Document   : ActualizarCliente
    Created on : 14/11/2020, 04:29:58
    Author     : lex
--%>

<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Registrar Cliente</h2>
            <hr>
            <div class="centrar">
                <form action="ModificarCliente" method="POST"  enctype="multipart/form-data" class="form-control" style="width: 500px; height: 850px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Codigo</h1>
                        <input type="text" name="codigo" class="form-control" readonly="" placeholder="Codigo" value="${cliente.getCodigo()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Nombre</h1>
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" value="${cliente.getNombre()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">DPI</h1>
                        <input type="number" name="dpi"  step="1" min="0" class="form-control" value="${cliente.getDpi()}" placeholder="DPI" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Direccion</h1>
                        <input type="text" name="direccion" class="form-control" placeholder="Direccion" value="${cliente.getDireccion()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Sexo</h1>
                        <select class="sexo" name="sexo" required="">
                            <option value="${cliente.getSexo()}">${cliente.getSexo()}</option>
                            <option value="MASCULINO">Masculino</option>
                            <option value="FEMENINO">Femenino</option>
                        </select>
                    </div>
                    <div class="form-group">
                         <h1 class="subtitulo">Fecha de nacimiento</h1>
                        <input type="date" name="fecha" min="1900-01-01" max="<%= LocalDate.now()%>" value="${cliente.getFecha_nacimiento()}" required="">
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">PDF con DPI escaneado</h1>
                        <input type="file" name="file" class="custom file input" id="file" style="color: black;" value="${cliente.getPdfDPI()}" accept="application/pdf" lang="es" >
                    </div>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/MostrarDPI?codigo=${cliente.getCodigo()}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Obtener DPI</a>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Password</h1>
                        <input type="password" name="password" class="form-control" placeholder="Password"  value="${cliente.getPassword()}" required=""/>
                    </div>
                    <br>
                    <input type="submit" value="Guardar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
