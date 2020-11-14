<%-- 
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
            <h2 class="title">Seleccionar cajero</h2>
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
                            <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerCajero?cajero_codigo=${cajero.getCodigo()}">Actualizar</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>