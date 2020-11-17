<%-- 
    Document   : CrearCajero
    Created on : 13/11/2020, 22:12:26
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Realizar Transferencia</h2>
            <hr>
            <c:if test="${mensaje==0}">
                <div class="form-group" style="background-color: #cc0000;">
                    <h1 class="title" style="color: #ffffff">No ingreso un monto correcto, intentelo de nuevo</h1>
                </div>
            </c:if>
            <div class="centrar">
                <form name="formulario" action="CrearTransferencia" method="POST" class="form-control" style="width: 500px; height: 650px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Cuenta de la que se debitara</h1>
                        <input type="text" name="cuenta_debito" class="form-control" placeholder="No hay informacion disponible" value="${cuenta_debito.getCodigo()}"  readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Propietario</h1>
                        <input type="text" name="nombre" class="form-control" placeholder="No hay informacion disponible" value="${cliente_actual.getNombre()}" readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Monto disponible</h1>
                        <input type="text" name="monto_disponible" class="form-control" placeholder="No hay informacion disponible" value="${cuenta_debito.getMonto()}" readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Cuenta a la que se acreeditara</h1>
                        <input type="text" name="cuenta_credito" class="form-control" placeholder="No hay informacion disponible" value="${cuenta_credito.getCodigo()}" readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Propietario</h1>
                        <input type="text" name="direccion" class="form-control" placeholder="No hay informacion disponible" value="${cliente_seleccionado.getNombre()}" readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Monto a transferir</h1>
                        <input type="number" name="monto_transferido" step="0.01" min="0" class="form-control" placeholder="Monto" value="" required=""/>
                    </div>
                    <br>
                    <input type="submit" value="Transferir" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>

