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
            <h2 class="title" >Registrar Cajero</h2>
            <hr>
            <div class="centrar">
                <form name="formulario" action="../CrearCajero" method="POST" class="form-control" style="width: 500px; height: 460px; background: #003366;">
                    <div class="form-group">
                        <input type="text" name="nombre" class="form-control" placeholder="Nombre" required=""/>
                    </div>
                    <div class="form-group">
                        <select class="sexo" name="turno" required="">
                            <option value="">Turno</option>
                            <option value="VESPERTINO">Vespertino</option>
                            <option value="MATUTINO">Matutino</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="number" name="dpi" step="1" min="0" class="form-control" placeholder="DPI" required=""/>
                    </div>
                    <div class="form-group">
                        <input type="text" name="direccion" class="form-control" placeholder="Direccion" required=""/>
                    </div>
                    <div class="form-group">
                        <select class="sexo" name="sexo" required="">
                            <option value="">Sexo</option>
                            <option value="Masculino">Masculino</option>
                            <option value="Femenino">Femenino</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Password" required=""/>
                    </div>
                    <br>
                    <input type="submit" value="Guardar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
