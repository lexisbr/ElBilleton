<%-- 
    Document   : ExitoCargaDatos
    Created on : 13/11/2020, 08:10:28
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Encabezado.jsp" %>
    <!--Informacion de usuario-->
    <section class="contenidoExito" style="">
        <c:if test="${mensaje==0}">
            <h2 class="title">Se ha realizado el deposito correctamente</h2>
            <h1 class="title">Cambio: Q.${cambio}</h1>
            <h1 class="title">Codigo de transaccion: ${transaccion_codigo}</h1>
        </c:if>
        <c:if test="${mensaje==1}">
            <h2 class="title">Se ha rechazado la solicitud correctamente</h2>
        </c:if>
    </section>
</body>
</html>
