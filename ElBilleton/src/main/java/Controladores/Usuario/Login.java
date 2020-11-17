/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Usuario;

import Modelos.Usuario.CajeroModel;
import Modelos.Usuario.ClienteModel;
import Modelos.Usuario.GerenteModel;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Cliente;
import Objetos.Usuarios.Gerente;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    ClienteModel clienteModel = new ClienteModel();
    GerenteModel gerenteModel = new GerenteModel();
    CajeroModel cajeroModel = new CajeroModel();

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
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        } catch (IOException | ServletException e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
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
        try {

            int username = Integer.parseInt(String.valueOf(request.getParameter("user")));
            String password = request.getParameter("password");
            String tipo = request.getParameter("tipo");

            if (tipo.equals("CLIENTE")) {
                Cliente cliente = clienteModel.validacionLogin(username, password);
                if (cliente != null) {
                    request.getSession().setAttribute("user", username);
                    request.getSession().setAttribute("usuario", cliente);
                    response.sendRedirect(request.getContextPath() + "/Cliente/IndexCliente.jsp");
                } else {
                    request.setAttribute("message", 0);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else if (tipo.equals("CAJERO")) {
                Cajero cajero = cajeroModel.validacionLogin(username, password);
                if (cajero != null) {
                    request.getSession().setAttribute("user", username);
                    request.getSession().setAttribute("usuario", cajero);
                    System.out.println("Ingresa cajero" + username);
                    response.sendRedirect(request.getContextPath() + "/EstadoTurnoCajero");
                } else {
                    request.setAttribute("message", 0);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else if (tipo.equals("GERENTE")) {
                Gerente gerente = gerenteModel.validacionLogin(username, password);
                if (gerente != null) {
                    request.getSession().setAttribute("user", username);
                    request.getSession().setAttribute("usuario", gerente);
                    response.sendRedirect(request.getContextPath() + "/EstadoTurnoGerente");
                } else {
                    request.setAttribute("message", 0);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", 0);
                request.getRequestDispatcher("/Login.jsp").forward(request, response);
            }

        } catch (SQLException | NumberFormatException | UnsupportedEncodingException e) {
            System.out.println("Login Error: " + e.getMessage());
            request.setAttribute("message", 0);
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }

}
