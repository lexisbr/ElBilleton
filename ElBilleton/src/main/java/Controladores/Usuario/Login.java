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
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
                    System.out.println("Ingresa cliente" + username);
                    // response.sendRedirect(request.getContextPath()+"");
                } else {
                    request.setAttribute("message", 0);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else if (tipo.equals("CAJERO")) {
                Cajero cajero = cajeroModel.validacionLogin(username, password);
                if (cajero != null) {
                    request.getSession().setAttribute("user", username);
                    System.out.println("Ingresa cajero" + username);
                    // response.sendRedirect(request.getContextPath()+"");
                } else {
                    request.setAttribute("message", 0);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else if (tipo.equals("GERENTE")) {
                Gerente gerente = gerenteModel.validacionLogin(username, password);
                if (gerente != null) {
                    request.getSession().setAttribute("user", username);
                    request.getSession().setAttribute("gerente", gerente);
                    response.sendRedirect(request.getContextPath()+"/EstadoTurnoGerente");
                } else {
                    request.setAttribute("message", 0);
                    request.getRequestDispatcher("/Login.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("message", 0);
                request.getRequestDispatcher("/Login.jsp").forward(request, response);
            }

        } catch (SQLException | NumberFormatException e) {
            System.out.println("Login Error: " + e.getMessage());
            e.printStackTrace();
            request.setAttribute("message", 0);
            request.getRequestDispatcher("/Login.jsp").forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
