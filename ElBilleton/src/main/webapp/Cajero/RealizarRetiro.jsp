<%-- 
    Document   : CrearCuenta
    Created on : 13/11/2020, 22:57:19
    Author     : lex
--%>

<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Realizar Retiro</h2>
            <c:if test="${mensaje==0}">
                <div class="form-group" style="background-color: #cc0000;">
                    <h1 class="title" style="color: #ffffff">No ingreso los datos correctamente</h1>
                </div>
            </c:if>
            <hr>
            <div class="centrar">
                <form action="${pageContext.request.contextPath}/RealizarRetiro" method="POST" class="form-control" style="width: 500px; height: 610px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Nombre de propietario</h1>
                        <input type="text" name="cliente_codigo" class="form-control" readonly="" required="" value="${cliente.getNombre()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">DPI de propietario</h1>
                        <input type="text" name="dpi" class="form-control" readonly="" required="" value="${cliente.getDpi()}"/>
                    </div>
                    <div class="form-group">
                        <a href="${pageContext.request.contextPath}/MostrarDPI?codigo=${cliente.getCodigo()}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Obtener DPI</a>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Numero de cuenta</h1>
                        <input type="text" name="cuenta_codigo" class="form-control" readonly=""  required="" value="${cuenta.getCodigo()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Monto actual de la cuenta</h1>
                        <input type="number"  min="1" step="0.05" name="monto_actual" class="form-control"  required="" readonly="" value="${cuenta.getMonto()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Monto a retirar</h1>
                        <input type="number" min="1" step="0.05" name="monto_retirar" class="form-control"  required="" placeholder="Ingrese monto a retirar"/>
                    </div>
                    <br>
                    <input type="submit" value="Enviar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
