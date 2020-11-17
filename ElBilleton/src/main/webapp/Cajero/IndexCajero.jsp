<%-- 
    Document   : IndexGerente
    Created on : 12/11/2020, 02:03:52
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
      <!-- Encabezado -->
      <%@include file="Encabezado.jsp" %>
       <section class="contenido">
          <h2 class="title">Bienvenido Cajero</h2>
          <h1>Codigo: ${usuario.getCodigo()}</h1>
          <h1>Nombre: ${usuario.getNombre()}</h1>
          <h1>DPI: ${usuario.getDpi()}</h1>
          <h1>Turno: ${usuario.getTurno()}</h1>
          <h1>Estado: ${estado}</h1>
      </section>
    </body>
</html>
