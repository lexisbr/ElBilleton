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
            <h2 class="title" >Actualizar Cajero</h2>
            <hr>
            <div class="centrar">
                <form name="formulario" action="ModificarCajero" method="POST" class="form-control" style="width: 500px; height: 690px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Codigo</h1>
                        <input type="text" name="codigo" class="form-control" placeholder="Nombre" value="${cajero.getCodigo()}"  readonly="" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Nombre</h1>
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" value="${cajero.getNombre()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Turno</h1>
                        <select class="sexo" name="turno" required="">
                            <option value="${cajero.getTurno()}">${cajero.getTurno()}</option>
                            <option value="VESPERTINO">VESPERTINO</option>
                            <option value="MATUTINO">MATUTINO</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">DPI</h1>
                        <input type="number" name="dpi"  step="1" min="0" class="form-control" placeholder="DPI" value="${cajero.getDpi()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Direccion</h1>
                        <input type="text" name="direccion" class="form-control" placeholder="Direccion" value="${cajero.getDireccion()}" required=""/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Sexo</h1>
                        <select class="sexo" name="sexo" required="">
                            <option value="${cajero.getSexo()}">${cajero.getSexo()}</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Password</h1>
                        <input type="password" name="password" class="form-control" placeholder="Password" value="${cajero.getPassword()}" required=""/>
                    </div>
                    <br>
                    <input type="submit" value="Guardar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
