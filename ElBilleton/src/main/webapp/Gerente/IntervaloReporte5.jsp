
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <div class="centrar">
                    <h2 class="title" >Cargar Archivo</h2>
                    <hr>
                <form action="../ImportarDatos" class="form-control" method="POST" enctype="multipart/form-data" style="width: 500px; height: 200px; background: #003366;">
                    <div class="form-group">
                        <input type="file" class="custom file input" id="file" style="color: black; "lang="es" name="archivos" multiple required>
                    </div>
                    <br>
                    <input type="submit" value="Cargar" class="guardar" name="guardar"/>
                </form>
            </div>
        </div> 
    </section>
</body>
</html>
