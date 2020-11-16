/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.GerenteModel;
import Objetos.Usuarios.Gerente;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet("/EstadoTurnoCrear")
public class EstadoTurnoCrear extends HttpServlet{
     GerenteModel gerenteModel = new GerenteModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            String opcion = request.getParameter("opcion");
            Gerente gerente = (Gerente) request.getSession().getAttribute("usuario");

            if (gerenteModel.estaDentroTurno(gerente.getCodigo())) {
                request.getSession().setAttribute("estado", "ACTIVO");
                
                switch (opcion) {
                    case "1":
                        response.sendRedirect(request.getContextPath() + "/Gerente/CrearCliente.jsp");
                        break;
                    case "2":
                        response.sendRedirect(request.getContextPath() + "/ObtenerClientes?opcion=0");
                        break;
                    case "3":
                        response.sendRedirect(request.getContextPath() + "/Gerente/CrearCajero.jsp");
                        break;
                    case "4":
                        response.sendRedirect(request.getContextPath() + "/Gerente/CrearGerente.jsp");
                        break;
                    case "5":
                        response.sendRedirect(request.getContextPath() + "/ObtenerClientesParaActualizar");
                        break;
                    case "6":
                        response.sendRedirect(request.getContextPath() + "/ObtenerCajeros?opcion=0");
                        break;
                     case "7":
                        response.sendRedirect(request.getContextPath() + "/ObtenerDatos");
                        break;
                     case "8":
                        response.sendRedirect(request.getContextPath() + "/ObtenerClientes?opcion=2");
                        break;
                     case "9":
                        response.sendRedirect(request.getContextPath() + "/ObtenerCajeros?opcion=2");
                        break;
                     case "10":
                        response.sendRedirect(request.getContextPath() + "/ObtenerGerentes?opcion=1");
                        break;
                    default:
                        break;
                }
        
                
            } else {
                request.setAttribute("exito", 0);
                request.getSession().setAttribute("estado", "INACTIVO");
                request.getRequestDispatcher("Gerente/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (SQLException | IOException | NumberFormatException  e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
