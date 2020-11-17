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
        <form action="" method="GET">
            <h2 class="title">Estado de cuenta</h2>
            <hr>
             <a href="ExportarReporte3Transacciones?codigo=${codigo}&fecha1=${fecha1}&fecha2=${fecha2}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            <br>
            <br>
            <div class="form-group" style="">
                <h1 style="font-size: 25px; font-weight: bold;">Numero de Cuenta: ${codigo}</h1>
            </div>
            <div class="form-group" style="">
                <h1 style="font-size: 25px; font-weight: bold;">Fecha inicio: ${fecha1}</h1>
            </div>
            <div class="form-group" style="">
                <h1 style="font-size: 25px; font-weight: bold;">Fecha final: ${fecha2}</h1>
            </div>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Codigo de Transaccion</th>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Hora</th>
                        <th class="text-center">Tipo</th>
                        <th class="text-center">Monto</th>
                    </tr>
                    <c:forEach items="${listaTransaccion}" var="transaccion">
                        <tr>
                            <td class="text-center">${transaccion.getCodigo()}</td>
                            <td class="text-center">${transaccion.getFecha()}</td>
                            <td class="text-center">${transaccion.getHora()}</td>
                            <td class="text-center">${transaccion.getTipo()}</td>
                            <td class="text-center">${transaccion.getMonto()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
