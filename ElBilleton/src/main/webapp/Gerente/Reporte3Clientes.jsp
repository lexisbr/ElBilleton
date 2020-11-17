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
            <h2 class="title">Clientes con transacciones sumadas mayores al limite</h2>
            <hr>
            <div class="wrap" style="margin-left: -100px; font-size: 25px">
                <h1>Limite: ${limite}</h1>
            </div>
            <a href="ExportarReporte3Clientes?limite=${limite}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
            <br>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered" style="font-size: 16px;">
                    <tr>
                        <th class="text-center">Codigo</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                        <th class="text-center">Fecha de nacimiento</th>
                        <th class="text-center">PDF de DPI</th>
                        <th class="text-center">Suma de transacciones</th>
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
                                <a href="${pageContext.request.contextPath}/MostrarDPI?codigo=${cliente.getCodigo()}" target="_blank" class="btn btn-secondary btn-sm" role="button" aria-pressed="true">Obtener DPI</a>
                            </td>
                            <td class="text-center">${cliente.getTotal_transacciones()}</td>
                            <td>
                                <a class="btn btn-warning btn-sm" href="${pageContext.request.contextPath}/ObtenerTransaccionesReporte3?codigo=${cliente.getCodigo()}">Mostrar transacciones</a>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
