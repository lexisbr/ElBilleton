/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.LimitesGerenteModel;
import Objetos.Banco.LimitesGerente;
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
@WebServlet(name = "ModificarLimites", urlPatterns = {"/ModificarLimites"})
public class ModificarLimites extends HttpServlet {

    LimitesGerenteModel limitesModel = new LimitesGerenteModel();

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
            Double limite1 = Double.parseDouble(request.getParameter("limite1"));
            Double limite2 = Double.parseDouble(request.getParameter("limite2"));
            if (limite1 < limite2) {
                LimitesGerente limites = new LimitesGerente(limite1, limite2);
                limitesModel.modificarLimites(limites);
                request.setAttribute("exito", 3);
                request.getRequestDispatcher("Gerente/ExitoModificarCajero.jsp").forward(request, response);
            }else{
               
                request.getRequestDispatcher("/CargarLimites").forward(request, response);
            }

        } catch (NumberFormatException e) {
            System.out.println("Error al modificar limite en servlet " + e);
        }
    }

}
