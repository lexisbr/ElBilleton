/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cajero;

import Modelos.Usuario.CajeroModel;
import Objetos.Usuarios.Cajero;
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
@WebServlet("/VerificarTurno")
public class VerificarTurno extends HttpServlet{
     CajeroModel cajeroModel = new CajeroModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            String opcion = request.getParameter("opcion");
            Cajero cajero = (Cajero) request.getSession().getAttribute("usuario");

            if (cajeroModel.estaDentroTurno(cajero.getCodigo())) {
                request.getSession().setAttribute("estado", "ACTIVO");
                
                switch (opcion) {
                    case "1":
                        response.sendRedirect(request.getContextPath() + "/Cajero/BuscarCuentaDeposito.jsp");
                        break;
                    case "2":
                        response.sendRedirect(request.getContextPath() + "/Cajero/BuscarCuentaRetiro.jsp");
                        break;
                }
        
                
            } else {
                request.setAttribute("exito", 0);
                request.getSession().setAttribute("estado", "INACTIVO");
                request.getRequestDispatcher("Cajero/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (SQLException | IOException | NumberFormatException  e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
}
