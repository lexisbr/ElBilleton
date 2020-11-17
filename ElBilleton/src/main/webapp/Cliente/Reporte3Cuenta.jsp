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
            <h2 class="title">Cuenta con mas dinero</h2>
            <hr>
            <a href="ExportarReporte3Cuenta?cliente_codigo=${cuenta.getCliente_codigo()}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            <br>
            <div class="form-group">
                <h1 class="subtitulo">Fecha inicio: ${fecha1}</h1>
            </div>
            <div class="form-group">
                <h1 class="subtitulo">Fecha final: ${fecha2}</h1>
            </div>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Numero de Cuenta</th>
                        <th class="text-center">Fecha de Creacion</th>
                        <th class="text-center">Monto</th>
                    </tr>
                    <tr>
                        <td class="text-center">${cuenta.getCodigo()}</td>
                        <td class="text-center">${cuenta.getFecha_creacion()}</td>
                        <td class="text-center">${cuenta.getMonto()}</td>
                        <td>
                            <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/Reporte3Transacciones?codigo=${cuenta.getCodigo()}&fecha1=${fecha1}&fecha2=${fecha2}">Ver transacciones</a>
                        </td>
                    </tr>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
