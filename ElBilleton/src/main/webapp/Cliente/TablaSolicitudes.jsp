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
            <h2 class="title">Seleccionar cuenta</h2>
            <hr>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Estado</th>
                        <th class="text-center">Cuenta que envia</th>
                    </tr>
                    <c:forEach items="${listaSolicitudes}" var="solicitudes">
                        <tr>
                            <td class="text-center">${solicitudes.getFecha()}</td>
                            <td class="text-center">${solicitudes.getEstado()}</td>
                            <td class="text-center">${solicitudes.getCuenta_codigo_envia()}</td>
                            <td>
                                <a class="btn btn-success btn-sm" href="${pageContext.request.contextPath}/AceptarSolicitud?solicitud_codigo=${solicitudes.getCodigo()}">Aceptar</a>
                                <a class="btn btn-danger btn-sm" href="${pageContext.request.contextPath}/RechazarSolicitud?solicitud_codigo=${solicitudes.getCodigo()}">Rechazar</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
