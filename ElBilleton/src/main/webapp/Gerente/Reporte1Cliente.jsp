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
            <h2 class="title">Historial de cambios de cliente</h2>
            <hr>
            <br>
            <div class="container">
                <table class="table table-dark table-bordered">
                    <tr>
                        <th class="text-center">Codigo de cambio</th>
                        <th class="text-center">Codigo de cliente</th>
                        <th class="text-center">Nombre</th>
                        <th class="text-center">DPI</th>
                        <th class="text-center">Direccion</th>
                        <th class="text-center">Sexo</th>
                        <th class="text-center">Fecha de nacimiento</th>
                    </tr>
                    <c:forEach items="${listaHistorial}" var="historialCliente">
                    <tr>
                        <td class="text-center">${historialCliente.getCodigo()}</td>
                        <td class="text-center">${historialCliente.getCliente_codigo()}</td>
                        <td class="text-center">${historialCliente.getNombre()}</td>
                        <td class="text-center">${historialCliente.getDpi()}</td>
                        <td class="text-center">${historialCliente.getDireccion()}</td>
                        <td class="text-center">${historialCliente.getSexo()}</td>
                        <td class="text-center">${historialCliente.getFecha_nacimiento()}</td>
                        <td>
                            <a href="${pageContext.request.contextPath}/MostrarDPIHistorial?codigo=${historialCliente.getCodigo()}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Obtener DPI</a>
                        </td>
                    </tr>
                    </c:forEach>
                </table>
            </div>
        </form>
    </section>
</body>
</html>
