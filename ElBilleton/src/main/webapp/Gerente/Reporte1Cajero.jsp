<%-- 
    Document   : Reporte1Cajero
    Created on : 15/11/2020, 00:34:11
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
     <!--Encabezado-->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <form action="ObtenerCajeros" method="GET">
            <h2 class="title">Historial de cambios de cajero</h2>
            <hr>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Codigo de Cambio</th>
                        <th class="text-center">Codigo de Cajero</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Turno</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                    </tr>
                    <c:forEach items="${listaHistorial}" var="cajero">
                    <tr>
                        <td class="text-center">${cajero.getCodigo()}</td>
                        <td class="text-center">${cajero.getCajero_codigo()}</td>
                        <td class="text-center">${cajero.getNombre()}</td>
                        <td class="text-center">${cajero.getTurno()}</td>
                        <td class="text-center">${cajero.getDpi()}</td>
                        <td class="text-center">${cajero.getDireccion()}</td>
                        <td class="text-center">${cajero.getSexo()}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>