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
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Enviar Solicitud de Asociacion</h2>
            <hr>
            <div class="centrar">
                <form action="${pageContext.request.contextPath}/CrearSolicitud" method="POST" class="form-control" style="width: 500px; height: 480px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Nombre de propietario</h1>
                        <input type="text" name="cliente_codigo" class="form-control" readonly="" required="" value="${cliente.getNombre()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">DPI de propietario</h1>
                        <input type="text" name="dpi" class="form-control" readonly="" required="" value="${cliente.getDpi()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Numero de cuenta</h1>
                        <input type="text" name="cuenta_recibe" class="form-control" readonly=""  required="" value="${cuenta.getCodigo()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Seleccione cuenta a asociar</h1>
                         <select class="form-control" name="cuenta_envia" required="">
                             <c:forEach items="${cuentasPersonales}" var="cuenta">
                             <option value="${cuenta.getCodigo()}">${cuenta.getCodigo()}</option>
                             </c:forEach>
                         </select>
                    </div>
                    <br>
                    <input type="submit" value="Enviar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
