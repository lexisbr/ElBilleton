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
    <%int cont=1; %>
    <section class="contenido">
        <form action="" method="GET">
            <input type="hidden" name="opcion" value="0">
            <h2 class="title">10 Clientes con mas dinero</h2>
            <hr>
            <a href="ExportarReporte4Clientes" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            <br>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered" style="font-size: 18px;">
                    <tr>
                        <th class="text-center">Numero</th>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                        <th class="text-center">Fecha de nacimiento</th>
                        <th class="text-center">PDF de DPI</th>
                        <th class="text-center">Dinero en sus cuentas</th>
                    </tr>
                    <c:forEach items="${listaClientes}" var="cliente">
                        <tr>
                            <td class="text-center"><%= cont++ %></td>
                            <td class="text-center">${cliente.getCodigo()}</td>
                            <td class="text-center">${cliente.getNombre()}</td>
                            <td class="text-center">${cliente.getDpi()}</td>
                            <td class="text-center">${cliente.getDireccion()}</td>
                            <td class="text-center">${cliente.getSexo()}</td>
                            <td class="text-center">${cliente.getFecha_nacimiento()}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/MostrarDPI?codigo=${cliente.getCodigo()}" target="_blank" class="btn btn-secondary btn-sm" role="button" aria-pressed="true">Obtener DPI</a>
                            </td>
                            <td class="text-center">${cliente.getTotal_transacciones()}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
