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
        <form action="ObtenerGerentes" method="GET">
            <c:if test="${opcion==0}">
                <h2 class="title">Seleccionar gerente</h2>
                <input type="hidden" name="opcion" value="0">
            </c:if>
            <c:if test="${opcion==1}">
                <h2 class="title">Visualizar gerentes</h2>
                <input type="hidden" name="opcion" value="1">
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
                    <c:forEach items="${listaGerentes}" var="gerente">
                        <tr>
                            <td class="text-center">${gerente.getCodigo()}</td>
                            <td class="text-center">${gerente.getNombre()}</td>
                            <td class="text-center">${gerente.getTurno()}</td>
                            <td class="text-center">${gerente.getDpi()}</td>
                            <td class="text-center">${gerente.getDireccion()}</td>
                            <td class="text-center">${gerente.getSexo()}</td>
                             <c:if test="${opcion==0}">
                            <td>
                                <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerHistorialUsuario?codigo=${gerente.getCodigo()}&&tipo=gerente">Historial de cambios</a>
                            </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
