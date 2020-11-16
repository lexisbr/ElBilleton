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
        <form action="ObtenerClientesParaReporte6" method="GET">
            <h2 class="title">Seleccionar cliente</h2>
            <hr>
            <div class="wrap">
                <select class="" name="filtro" required="">
                    <option value="">Filtro</option>
                    <option value="NOMBRE">Por nombre</option>
                    <option value="DINERO">Mayor a cantidad de dinero</option>
                </select>
            </div>
            <div class="wrap">
                <%@include file="SearchBar.html" %>
            </div>
            <br>

            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                        <th class="text-center">Fecha de nacimiento</th>
                    </tr>
                    <c:forEach items="${listaClientes}" var="cliente">
                        <tr>
                            <td class="text-center">${cliente.getCodigo()}</td>
                            <td class="text-center">${cliente.getNombre()}</td>
                            <td class="text-center">${cliente.getDpi()}</td>
                            <td class="text-center">${cliente.getDireccion()}</td>
                            <td class="text-center">${cliente.getSexo()}</td>
                            <td class="text-center">${cliente.getFecha_nacimiento()}</td>
                            <td>
                                <input type="hidden" name="opcion" value="0">
                                <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerTransaccionesReporte6?cliente_codigo=${cliente.getCodigo()}">Transacciones</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
