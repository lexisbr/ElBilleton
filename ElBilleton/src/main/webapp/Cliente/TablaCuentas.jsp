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
            <input type="hidden" name="opcion" value="0">
            <hr>
            <div class="wrap">
                <%@include file="SearchBar.html" %>
            </div>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Numero de Cuenta</th>
                        <th class="text-center">Fecha de Creacion</th>
                        <th class="text-center">Monto</th>
                    </tr>
                    <c:forEach items="${listaCuentas}" var="cuenta">
                        <tr>
                            <td class="text-center">${cuenta.getCodigo()}</td>
                            <td class="text-center">${cuenta.getFecha_creacion()}</td>
                            <td class="text-center">${cuenta.getMonto()}</td>
                            <td>
                                <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/EstadoDeCuenta?cuenta_codigo=${cuenta.getCodigo()}">Ver estado</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
