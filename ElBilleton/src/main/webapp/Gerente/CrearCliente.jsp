<%-- 
    Document   : CrearCliente
    Created on : 13/11/2020, 17:06:15
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
                <form action="../CrearCliente" method="POST"  onsubmit="return noespacios()" enctype="multipart/form-data" class="form-control" style="width: 500px; height: 750px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha actual</h1>
                        <input type="date" name="fecha" value="<%=LocalDate.now()%>" readonly="" required="">
                    </div>
                    <div class="form-group">
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required=""/>
                    </div>
                    <div class="form-group">
                        <input type="number" name="dpi"  step="1" min="0" class="form-control" placeholder="DPI" required=""/>
                    </div>
                    <div class="form-group">
                        <input type="text" name="direccion" class="form-control" placeholder="Direccion" required=""/>
                    </div>
                    <div class="form-group">
                        <select class="sexo" name="sexo" required="">
                            <option value="">Sexo</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha de nacimiento</h1>
                        <input type="date" name="fecha" value="" min="1900-01-01" max="<%= LocalDate.now()%>" required="">
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">PDF con DPI escaneado</h1>
                        <input type="file" name="pdfDpi" class="custom file input" id="file" style="color: black;" accept="application/pdf" lang="es" required>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Password" required=""/>
                    </div>
                    <br>
                    <div class="form-group">
                        <h1 class="subtitulo">Monto para abrir cuenta</h1>
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