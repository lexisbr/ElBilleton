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
@WebServlet("/EstadoTurnoArchivo")
public class EstadoTurnoArchivo extends HttpServlet {

    GerenteModel gerenteModel = new GerenteModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {

            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }

            Gerente gerente = (Gerente) request.getSession().getAttribute("gerente");
            System.out.println("Gerente " + gerente.getCodigo());

            if (gerenteModel.estaDentroTurno(gerente.getCodigo())) {
                request.getSession().setAttribute("estado", "ACTIVO");
                request.getRequestDispatcher("/Gerente/CargarDatos.jsp").forward(request, response);
            } else {
                request.getSession().setAttribute("estado", "INACTIVO");
                request.getRequestDispatcher("/Gerente/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (SQLException | IOException | NumberFormatException | ServletException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
