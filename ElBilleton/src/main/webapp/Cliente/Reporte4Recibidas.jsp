<%-- 
    Document   : TablaCuentas
    Created on : 16/11/2020, 15:34:27
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--Encabezado-->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <form action="ObtenerCuentas" method="GET">
            <h2 class="title">Solicitudes de asociacion de cuenta recibidas</h2>
            <hr>
             <a href="ExportarReporte4Solicitudes?cliente_codigo=${cliente_codigo}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            <br>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Estado</th>
                        <th class="text-center">Cuenta que envia</th>
                        <th class="text-center">Cuenta que recibe</th>
                    </tr>
                    <c:forEach items="${listaSolicitudes}" var="solicitudes">
                        <tr>
                            <td class="text-center">${solicitudes.getFecha()}</td>
                            <td class="text-center">${solicitudes.getEstado()}</td>
                            <td class="text-center">${solicitudes.getCuenta_codigo_envia()}</td>
                            <td class="text-center">${solicitudes.getCuenta_codigo_recibe()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
