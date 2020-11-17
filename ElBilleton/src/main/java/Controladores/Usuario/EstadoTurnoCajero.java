/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Usuario;

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
@WebServlet("/EstadoTurnoCajero")
public class EstadoTurnoCajero extends HttpServlet {

    CajeroModel cajeroModel = new CajeroModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
       try {

            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }

            Cajero cajero = (Cajero) request.getSession().getAttribute("usuario");
            System.out.println("Cajero " + cajero.getCodigo());
            
            if (cajeroModel.estaDentroTurno(cajero.getCodigo())) {
                request.getSession().setAttribute("estado", "ACTIVO");
                response.sendRedirect(request.getContextPath() + "/Cajero/IndexCajero.jsp");
            }else {
                request.getSession().setAttribute("estado", "INACTIVO");
                response.sendRedirect(request.getContextPath() + "/Cajero/IndexCajero.jsp");
            }

        } catch (SQLException | IOException | NumberFormatException  e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

}
