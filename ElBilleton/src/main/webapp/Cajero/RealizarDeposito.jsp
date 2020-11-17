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
            <h2 class="title" >Realizar Deposito</h2>
            <c:if test="${mensaje==0}">
                <div class="form-group" style="background-color: #cc0000;">
                    <h1 class="title" style="color: #ffffff">No ingreso los datos correctamente</h1>
                </div>
            </c:if>
            <hr>
            <div class="centrar">
                <form action="${pageContext.request.contextPath}/RealizarDeposito" method="POST" class="form-control" style="width: 500px; height: 550px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Nombre de propietario</h1>
                        <input type="text" name="cliente_codigo" class="form-control" readonly="" required="" value="${cliente.getNombre()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">DPI de propietario</h1>
                        <input type="text" name="dpi" class="form-control" readonly="" required="" value="${cliente.getDpi()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Numero de cuenta</h1>
                        <input type="text" name="cuenta_codigo" class="form-control" readonly=""  required="" value="${cuenta.getCodigo()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Monto a depositar</h1>
                        <input type="number"  min="1" step="0.05" name="monto" class="form-control"  required="" placeholder="Ingrese monto a depositar"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Efectivo recibido</h1>
                        <input type="number" min="1" step="0.05" name="efectivo" class="form-control"  required="" placeholder="Ingrese efectivo recibido"/>
                    </div>
                    <br>
                    <input type="submit" value="Enviar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
