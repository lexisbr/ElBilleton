/<%-- 
    Document   : CrearCuentaTablaClientes
    Created on : 13/11/2020, 22:59:05
    Author     : lex
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!--Encabezado-->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <form action="ObtenerCajeros" method="GET">
            <c:if test="${opcion==0}">
                <h2 class="title">Seleccionar cajero</h2>
                <input type="hidden" name="opcion" value="0">
            </c:if>
            <c:if test="${opcion==1}">
                <h2 class="title">Seleccionar cajero</h2>
                <input type="hidden" name="opcion" value="1">
            </c:if>
            <c:if test="${opcion==2}">
                <h2 class="title">Visualizar cajero</h2>
                <input type="hidden" name="opcion" value="2">
            </c:if>
            <hr>
            <div class="wrap">
                <%@include file="SearchBar.html" %>
            </div>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">Turno</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                    </tr>
                    <c:forEach items="${listaCajeros}" var="cajero">
                        <tr>
                            <td class="text-center">${cajero.getCodigo()}</td>
                            <td class="text-center">${cajero.getNombre()}</td>
                            <td class="text-center">${cajero.getTurno()}</td>
                            <td class="text-center">${cajero.getDpi()}</td>
                            <td class="text-center">${cajero.getDireccion()}</td>
                            <td class="text-center">${cajero.getSexo()}</td>
                            <td>
                                <c:if test="${opcion==0}">
                                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerCajero?cajero_codigo=${cajero.getCodigo()}">Actualizar</a>
                                </c:if>
                                <c:if test="${opcion==1}">
                                    <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerHistorialUsuario?codigo=${cajero.getCodigo()}&&tipo=cajero">Historial de cambios</a>
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
