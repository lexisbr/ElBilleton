/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet(name = "CargarTablaUsuarios", urlPatterns = {"/CargarTablaUsuarios"})
public class CargarTablaUsuarios extends HttpServlet {

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            String usuario = request.getParameter("usuario");
            System.out.println("usuario "+usuario);
            switch (usuario) {
                case "CLIENTE":
                    response.sendRedirect(request.getContextPath() + "/ObtenerClientes?opcion=1");
                    break;
                case "CAJERO":
                    response.sendRedirect(request.getContextPath() + "/ObtenerCajeros?opcion=1");
                    break;
                case "GERENTE":
                    response.sendRedirect(request.getContextPath() + "/ObtenerGerentes?opcion=0");
                    break;
                default:
                    break;
            }

        } catch (IOException e) {
            System.out.println("Error al cargar usuario "+e);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

}
