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
            <h2 class="title">Se ha enviado solicitud correctamente</h2>
        </c:if>
    </section>
</body>
</html>
