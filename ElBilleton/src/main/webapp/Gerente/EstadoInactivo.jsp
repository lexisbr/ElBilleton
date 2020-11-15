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
    <c:if test="${exito==0}">
        <section class="contenidoExito" style="background-color: #cc0000">
            <h2 class="title">No se encuentra dentro de su turno</h2>
        </section>
    </c:if>

    <c:if test="${exito==1}">
        <section class="contenidoExito" style="background-color: #cc0000">
            <h2 class="title">Ingreso algun espacio vacio en los campos</h2>
        </section>
    </c:if>
   


</body>
</html>
