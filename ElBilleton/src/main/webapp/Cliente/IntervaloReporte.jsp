
<%@page import="java.time.LocalDate"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <!-- Encabezado -->
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <div class="centrar">
                    <h2 class="title" >Escoger intervalo para reporte</h2>
                    <hr>
                    <form action="${pageContext.request.contextPath}/Reporte3" class="form-control" method="GET" enctype="multipart/form-data" style="width: 500px; height: 300px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha inicio</h1>
                        <input type="date" name="fecha1" value="" min="1800-01-01" max="<%=LocalDate.now()%>" required="">
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha final</h1>
                        <input type="date" name="fecha2"  readonly="" value="<%=LocalDate.now()%>" required="">
                    </div>
                    <br>
                    <input type="submit" value="Continuar" class="guardar" name="guardar"/>
                </form>
            </div>
        </div> 
    </section>
</body>
</html>
