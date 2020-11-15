<%-- 
    Document   : EstablecerLimites
    Created on : 14/11/2020, 18:19:20
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%@include file="Encabezado.jsp" %>
    <section class="contenido">
        <div class="container">
            <h2 class="title" >Establecer Limites</h2>
            <hr>
            <div class="centrar">
                 <c:if test="${exito==2}">
                        <div class="form-group" style="background-color: #cc0000;">
                            <h1 class="title" style="color: #ffffff">El limite 1 no cumple con la condicion de ser menor</h1>
                        </div>
                    </c:if>
                <form action="${pageContext.request.contextPath}/ModificarLimites" method="POST" class="form-control" style="width: 500px; height: 400px; background: #003366;">
                    <div class="form-group">
                        <h1 class="subtitulo" style="color: #ffffff; font-style: italic; font-weight: bold;">El primer limite debe ser menor</h1>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Limite de Reporte "Ver clientes con transacciones monetarias mayores a un limite"</h1>
                        <input type="number" name="limite1" class="form-control" min="0" step=".01" required="" placeholder="Limite menor" value="${limites.getLimite_reporte2()}"/>
                    </div>
                    <div class="form-group">
                        <h1 class="subtitulo">Limite de Reporte "Ver clientes con transacciones monetarias sumadas mayores a un limite"</h1>
                        <input type="number" name="limite2" class="form-control" min="0" placeholder="Limite mayor"  value="${limites.getLimite_reporte3()}" required="" step=".01"/>
                    </div>
                    <br>
                    <input type="submit" value="Guardar" class="guardar"/>
                </form>
            </div>
        </div>
    </section>
</body>
</html>
