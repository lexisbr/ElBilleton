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
          <h2 class="title">Bienvenido Gerente</h2>
          <h1>Codigo: ${gerente.getCodigo()}</h1>
          <h1>Nombre: ${gerente.getNombre()}</h1>
          <h1>DPI: ${gerente.getDpi()}</h1>
          <h1>Turno: ${gerente.getTurno()}</h1>
          <h1>Estado: ${estado}</h1>
      </section>
    </body>
</html>
