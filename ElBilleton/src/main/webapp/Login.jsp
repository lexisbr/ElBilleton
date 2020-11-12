<%-- 
    Document   : Login
    Created on : 11/11/2020, 21:11:56
    Author     : lex
--%>

<%@page import="Modelos.Conexion.Conexion"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <title>Login | El Billeton</title>
        <link rel="stylesheet" href="css/style.css?3.0">
    </head>
    <body id="login">
        <div class="login-box">
            <img class="logo" src="img/logo1.png" alt="Logo hospital">
            <h1>El Billeton</h1>
            
            <c:if test="${message == 0}">
                 <h3 class="error">Las credenciales no son validas.</h3>
            </c:if>
          
            <form class="" action="Login" method="POST">
                <!--USUARIO-->
                <select name="tipo">
                    <option value="0">Seleccione su tipo de usuario.</option>
                    <option value="CLIENTE">Cliente</option>
                    <option value="CAJERO">Cajero</option>
                    <option value="GERENTE">Gerente</option>
                </select>

                <label for="usuario">Usuario</label>
                <input type="text" name="user" placeholder="Ingresa tu usuario.">

                <!--CONTRASEÑA-->
                <label for="password">Contraseña</label>
                <input type="password" name="password" placeholder="Ingresa tu contraseña.">

                <input type="submit" value="Ingresar">
            </form>
        </div>
    </body>
</html>
