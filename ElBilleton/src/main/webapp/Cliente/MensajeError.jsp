<%-- 
    Document   : EstadoInactivo
    Created on : 13/11/2020, 02:24:33
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Encabezado.jsp" %>
    <!--Informacion de usuario-->
    <section class="contenidoExito" style="background-color: #cc0000">
        <c:if test="${mensaje==0}">
            <h2 class="title">Ya no puede enviar mas solicitudes a esta cuenta</h2>
        </c:if>
    </section>
</body>
</html>
