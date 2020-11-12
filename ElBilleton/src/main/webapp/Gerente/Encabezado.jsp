<%-- 
    Document   : Encabezado
    Created on : 12/11/2020, 02:18:15
    Author     : lex
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<!DOCTYPE html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerente | El Billeton</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css?8.9">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?3.0">
</head>
<body id="index">


    <header id="encabezado">
        <h1>
            <img id="logoEncabezado" src="${pageContext.request.contextPath}/img/logo1.png" alt="logo">
            <h1 id="tituloEncabezado">El Billeton</h1>
        </h1>
    </header>
    <!--NAVIGATION-->
    <nav class="navbar">
        <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/Gerente/IndexGerente.jsp">Inicio</a></li>
            <li><a href="#">Crear</a>
                <ul class="submenu">
                    <li><a href="">Cliente</a></li>
                    <li><a href="">Cuenta</a></li>
                    <li><a href="">Cajero</a></li>
                    <li><a href="">Gerente</a></li>
                </ul>
            </li>
            <li><a href="#">Actualizar</a>
                <ul class="submenu">
                    <li><a href="">Cliente</a></li>
                    <li><a href="">Cajero</a></li>
                    <li><a href="">Mis datos</a></li>
                </ul>
            </li>
            <li><a href="#">Opciones</a>
                <ul class="submenu">
                    <li><a href="">Establecer limites para reportes</a></li>
                    <li><a href="">Cargar archivos</a></li>
                </ul>
            </li>
            <li><a href="#">Reporte</a>
                <ul class="submenu">
                    <li><a href="">Reporte</a></li>
                </ul>
            </li>
            <li><a href="../Login.jsp">Cerrar Sesion</a></li>
        </ul>
    </nav>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    
    if (session.getAttribute("user") == null) {
        response.sendRedirect("Login");
    }
%>