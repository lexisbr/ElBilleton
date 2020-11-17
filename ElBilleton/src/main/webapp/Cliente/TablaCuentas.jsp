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
            <div class="wrap">
                <%@include file="SearchBar.html" %>
            </div>
            <br>
            <c:if test="${opcion==0}">
                <input type="hidden" name="opcion" value="0">
            </c:if>
            <c:if test="${opcion==1}">
                <input type="hidden" name="opcion" value="1">
            </c:if>
            <c:if test="${opcion==2}">
                <input type="hidden" name="opcion" value="2">
            </c:if>    
            <c:if test="${opcion==3}">
                <input type="hidden" name="opcion" value="3">
            </c:if>    
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
                                <c:if test="${opcion==0}">
                                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/EstadoDeCuenta?cuenta_codigo=${cuenta.getCodigo()}">Ver estado</a>
                                </c:if>
                                <c:if test="${opcion==1}">
                                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerSolicitudesPendientes?cuenta_codigo=${cuenta.getCodigo()}">Ver solicitudes</a>
                                </c:if>
                                <c:if test="${opcion==2}">
                                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/CargarCuentasTransaccion?cuenta_codigo=${cuenta.getCodigo()}">Seleccionar para transferencia</a>
                                </c:if>    
                                <c:if test="${opcion==3}">
                                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/Reporte1?cuenta_codigo=${cuenta.getCodigo()}">Visualizar 15 transacciones</a>
                                </c:if>    
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
