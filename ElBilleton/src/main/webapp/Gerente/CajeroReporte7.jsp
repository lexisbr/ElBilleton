<%-- 
    Document   : ActualizarCajero
    Created on : 14/11/2020, 08:05:17
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Cajero con mas transacciones</h2>
            <div class="form-group" style="font-size: 25px">
                <h1>Fecha inicio: ${fecha1}</h1>
                <h1>Fecha final: ${fecha2}</h1>
            </div>
            <hr>
            <div class="centrar">
                <form name="formulario" action="ModificarCajero" method="POST" class="form-control" style="width: 500px; height: 580px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Codigo</h1>
                        <input type="text" name="codigo" class="form-control" placeholder="No hay transacciones" value="${cajero.getCodigo()}"  readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Nombre</h1>
                        <input type="text" name="nombre" class="form-control" placeholder="No hay transacciones" readonly="" value="${cajero.getNombre()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Turno</h1>
                        <input type="text" name="nombre" class="form-control" placeholder="No hay transacciones" readonly="" value="${cajero.getTurno()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">DPI</h1>
                        <input type="number" name="dpi"  step="1" min="0" class="form-control" placeholder="No hay transacciones" readonly="" value="${cajero.getDpi()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Direccion</h1>
                        <input type="text" name="direccion" class="form-control" placeholder="No hay transacciones" readonly="" value="${cajero.getDireccion()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Sexo</h1>
                        <input type="text" name="direccion" class="form-control" placeholder="No hay transacciones" readonly="" value="${cajero.getSexo()}" required=""/>
                    </div>
                    <br>
                    <a href="ExportarReporte7?fecha1=${fecha1}&fecha2=${fecha2}" target="_blank" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Exportar</a>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
