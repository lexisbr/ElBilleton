
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <div class="centrar">
                <form action="ImportarDatos" class="form-control" method="POST" enctype="multipart/form-data" style="background: #ccccff;">
                    <h2 class="title" >Cargar Archivo</h2>
                    <div class="form-group">
                        <input type="file" class="custom file input" id="file" style="color: black; "lang="es" name="archivos" multiple required>
                    </div>
                    <br>
                    <input type="submit" value="Guardar" class="guardar" name="guardar"/>
                </form>
            </div>
        </div> 
    </section>
</body>
</html>
