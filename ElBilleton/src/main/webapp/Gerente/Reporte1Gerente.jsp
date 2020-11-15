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
        <form action="" method="GET">
            <h2 class="title">Historial de cambios de gerente</h2>
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
                    <c:forEach items="${listaHistorial}" var="gerente">
                    <tr>
                        <td class="text-center">${gerente.getCodigo()}</td>
                        <td class="text-center">${gerente.getGerente_codigo()}</td>
                        <td class="text-center">${gerente.getNombre()}</td>
                        <td class="text-center">${gerente.getTurno()}</td>
                        <td class="text-center">${gerente.getDpi()}</td>
                        <td class="text-center">${gerente.getDireccion()}</td>
                        <td class="text-center">${gerente.getSexo()}</td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
