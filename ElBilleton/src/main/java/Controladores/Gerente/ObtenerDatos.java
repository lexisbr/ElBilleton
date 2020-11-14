/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.CajeroModel;
import Objetos.Usuarios.Gerente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet("/ObtenerDatos")
public class ObtenerDatos extends HttpServlet {

    CajeroModel cajeroModel = new CajeroModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            Gerente gerente = (Gerente) request.getSession().getAttribute("usuario");
            
            request.setAttribute("gerente", gerente);
                
            request.getRequestDispatcher("Gerente/ActualizarGerente.jsp").forward(request, response);

        } catch (NullPointerException | NumberFormatException |IOException | ServletException e) {
            System.out.println("Error buscar cliente: " + e.getMessage());
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
