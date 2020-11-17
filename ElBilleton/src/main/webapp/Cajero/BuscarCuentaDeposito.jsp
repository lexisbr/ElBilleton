<%-- 
    Document   : IngresarCuentaAsociacion
    Created on : 15/11/2020, 20:59:02
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
                <h2 class="title" >Realizar Deposito</h2>
                <hr>
                <c:if test="${exito==0}">
                    <div class="form-group" style="background-color: #cc0000;">
                        <h1 class="title" style="color: #ffffff">La cuenta no existe</h1>
                    </div>
                </c:if>
                <form action="${pageContext.request.contextPath}/ObtenerCuentaDeposito" class="form-control" method="GET" enctype="multipart/form-data" style="width: 500px; height: 250px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Ingresa numero de cuenta</h1>
                        <br>
                        <input type="number" min="0" name="cuenta_codigo"  class="form-control" placeholder="Numero de cuenta" required="">
                    </div>
                    <br>
                    <input type="submit" value="Continuar" class="guardar" name="guardar"/>
                </form>
            </div>
        </div> 
    </section>
</body>
</html>
