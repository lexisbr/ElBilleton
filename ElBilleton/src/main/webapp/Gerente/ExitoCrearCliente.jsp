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
          <h2 class="title">Se ha creado cliente exitosamente</h2>
          <h1>Codigo del cliente: ${cliente_codigo}</h1>
          <h1>Codigo de la cuenta: ${cuenta_codigo}</h1>
      </section>
    </body>
</html>
