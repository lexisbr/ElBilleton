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
                
                if(opcion.equals("1")){
                    response.sendRedirect(request.getContextPath() + "/Gerente/CrearCliente.jsp");
                }else if(opcion.equals("2")){
                    response.sendRedirect(request.getContextPath() + "/ObtenerClientes");
                }else if(opcion.equals("3")){
                    response.sendRedirect(request.getContextPath() + "/Gerente/CrearCajero.jsp");
                }
                
            } else {
                request.getSession().setAttribute("estado", "INACTIVO");
                response.sendRedirect(request.getContextPath() + "/Gerente/EstadoInactivo.jsp");
            }

        } catch (SQLException | IOException | NumberFormatException  e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
