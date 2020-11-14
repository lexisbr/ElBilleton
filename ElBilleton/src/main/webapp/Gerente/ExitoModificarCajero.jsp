<%-- 
    Document   : ExitoCrearCliente
    Created on : 13/11/2020, 19:23:20
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Encabezado.jsp" %>
    <!--Informacion de usuario-->
    <section class="contenidoExito" style="">
        <c:if test="${exito==1}">
            <h2 class="title">Se ha actualizado cajero exitosamente</h2>
            <h1>Codigo del cliente: ${cajero_codigo}</h1>
        </c:if>
        <c:if test="${exito==1}">
            <h2 class="title">Se ha actualizado gerente exitosamente</h2>
            <h1>Codigo del gerente: ${gerente_codigo}</h1>
        </c:if>


    </section>
</body>
</html>
