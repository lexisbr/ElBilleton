
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
                     <c:if test="${exito==2}">
                        <div class="form-group" style="background-color: #cc0000;">
                            <h1 class="title" style="color: #ffffff">No ingreso correctamente el intervalo, intentelo de nuevo</h1>
                        </div>
                     </c:if>
                    <form action="${pageContext.request.contextPath}/CargarReporteIntervalo" class="form-control" method="GET" enctype="multipart/form-data" style="width: 500px; height: 300px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha inicio</h1>
                        <input type="date" name="fecha1" value="" min="1800-01-01" max="3000-12-31" required="">
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Fecha final</h1>
                        <input type="date" name="fecha2" value="" min="1800-01-01" max="3000-12-31" required="">
                    </div>
                        <input name="opcion" value="<%=request.getParameter("opcion")%>" type="hidden"/>
                    <br>
                    <input type="submit" value="Continuar" class="guardar" name="guardar"/>
                </form>
            </div>
        </div> 
    </section>
</body>
</html>
