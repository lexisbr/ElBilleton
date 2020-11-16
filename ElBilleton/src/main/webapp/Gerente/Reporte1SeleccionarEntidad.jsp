<%-- 
    Document   : Reporte1SeleccionarEntidad
    Created on : 14/11/2020, 20:58:35
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <div class="centrar">
                    <h2 class="title" >Seleccione tipo de usuario</h2>
                    <hr>
                <form action="${pageContext.request.contextPath}/CargarTablaUsuarios" class="form-control" method="GET" enctype="multipart/form-data" style="width: 500px; height: 200px; background: #003366;">
                     <div class="form-group">
                         <select class="sexo" name="usuario" required="">
                             <option value="">Usuarios</option>
                             <option value="CLIENTE">Cliente</option>
                             <option value="CAJERO">Cajero</option>
                             <option value="GERENTE">Gerente</option>
                         </select>
                    </div>
                    <br>
                    <input type="submit" value="Continuar" class="guardar" name="guardar"/>
                </form>
            </div>
        </div> 
    </section>
</body>
</html>
