
<%-- 
    Document   : Reporte1Cliente
    Created on : 14/11/2020, 22:04:52
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--Encabezado-->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <form action="" method="GET">
            <input type="hidden" name="opcion" value="0">
            <h2 class="title">Transacciones del Cliente</h2>
            <hr>
            <div class="form-group" style="margin-left: -40px;">
                <h1 style="font-size: 35px;">Cliente: ${cliente_codigo}</h1>
            </div>
            </div>
            <c:if test="${limite!=null}">
             <a href="ExportarReporte6?limite=${limite}&codigo=${cliente_codigo}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            </c:if>
            <c:if test="${limite==null}">
             <a href="ExportarReporte6?limite=0&codigo=${cliente_codigo}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            </c:if>
            <br>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered" style="font-size: 15px;">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Fecha</th>
                        <th class="text-center">Hora</th>
                        <th class="text-center">Tipo</th>
                        <th class="text-center">Monto</th>
                        <th class="text-center">Codigo de Cuenta</th>
                        <th class="text-center">Codigo de Cajero</th>
                    </tr>
                    <c:forEach items="${listaTransacciones}" var="transaccion">
                        <tr>
                            <td class="text-center">${transaccion.getCodigo()}</td>
                            <td class="text-center">${transaccion.getFecha()}</td>
                            <td class="text-center">${transaccion.getHora()}</td>
                            <td class="text-center">${transaccion.getTipo()}</td>
                            <td class="text-center">${transaccion.getMonto()}</td>
                            <td class="text-center">${transaccion.getCuenta_codigo()}</td>
                            <td class="text-center">${transaccion.getCajero_codigo()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
