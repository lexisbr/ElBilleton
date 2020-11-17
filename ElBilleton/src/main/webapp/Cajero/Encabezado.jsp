<%-- 
    Document   : Encabezado
    Created on : 12/11/2020, 02:18:15
    Author     : lex
--%>

<%@page import="Objetos.Usuarios.Cajero"%>
<%@page import="Modelos.Usuario.CajeroModel"%>
<%@page import="Modelos.Usuario.ClienteModel"%>
<%@page import="Objetos.Usuarios.Cliente"%>
<%@page import="Objetos.Usuarios.Gerente"%>
<%@page import="Modelos.Usuario.GerenteModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Cajero | El Billeton</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css?8.9">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css?6.3">
</head>
<body id="index">
    <header id="encabezado">
            <img id="logoEncabezado" src="${pageContext.request.contextPath}/img/logo1.png" alt="logo">
            <h1 id="tituloEncabezado">El Billeton</h1>
    </header>
    <!--NAVIGATION-->
    <nav class="navbar">
        <ul class="menu">
            <li><a href="${pageContext.request.contextPath}/Cajero/IndexCajero.jsp">Inicio</a></li>
            <li><a href="${pageContext.request.contextPath}/VerificarTurno?opcion=1">Depositos</a></li>
            <li><a href="${pageContext.request.contextPath}/VerificarTurno?opcion=2">Retiros</a></li>
            <li><a href="#" style="width: 180;">Reportes</a>
                <ul class="submenu">
                    <li><a href="${pageContext.request.contextPath}/ObtenerCuentas?opcion=3"></a></li>
                </ul>
            </li>
            <li><a href="${pageContext.request.contextPath}/Logout">Cerrar Sesion</a></li>
        </ul>
    </nav>
    <%

        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

        if (session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/Login");
        } else {
            CajeroModel cajeroModel = new CajeroModel();

            Cajero cajero = cajeroModel.obtenerCajero(Long.parseLong(session.getAttribute("user").toString()));

            session.setAttribute("usuario", cajero);
        }
    %>