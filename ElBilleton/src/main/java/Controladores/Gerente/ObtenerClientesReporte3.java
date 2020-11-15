/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.LimitesGerenteModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.LimitesGerente;
import Objetos.Usuarios.Cliente;
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
@WebServlet(name = "ObtenerClientesReporte3", urlPatterns = {"/ObtenerClientesReporte3"})
public class ObtenerClientesReporte3 extends HttpServlet {

    ClienteModel clienteModel = new ClienteModel();
    LimitesGerenteModel limiteModel = new LimitesGerenteModel();

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
            LimitesGerente limiteGerente = limiteModel.obtenerLimites();
            
            ArrayList<Cliente> listaClientes = clienteModel.conTransaccionesSumadasMayores(limiteGerente.getLimite_reporte3());
            
            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("limite", limiteGerente.getLimite_reporte3());
            
            request.getRequestDispatcher("/Gerente/Reporte3Clientes.jsp").forward(request, response);
            

        } catch (IOException | ServletException e) {
            System.out.println("Error al cargar datos de reporte 3 "+e);
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
