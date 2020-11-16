/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.GerenteModel;
import Objetos.Usuarios.Gerente;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet(name = "ObtenerGerentes", urlPatterns = {"/ObtenerGerentes"})
public class ObtenerGerentes extends HttpServlet {

    GerenteModel gerenteModel = new GerenteModel();

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
            if (request.getSession().getAttribute("usuario") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            String codigo = request.getParameter("campo");
            ArrayList<Gerente> listaGerentes;

            if (codigo == null || (codigo != null && codigo.isEmpty())) {
                listaGerentes = gerenteModel.obtenerGerentes();
            } else {
                listaGerentes = gerenteModel.obtenerGerentesFiltrando(Long.parseLong(codigo));
            }

            request.setAttribute("listaGerentes", listaGerentes);
            String opcion = request.getParameter("opcion");
            switch (opcion) {
                case "0":
                    request.setAttribute("opcion", 0);
                    request.getRequestDispatcher("/Gerente/TablaGerentes.jsp").forward(request, response);
                    break;
                case "1":
                    request.setAttribute("opcion", 1);
                    request.getRequestDispatcher("/Gerente/TablaGerentes.jsp").forward(request, response);
                    break;
            }

        } catch (Exception e) {
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
