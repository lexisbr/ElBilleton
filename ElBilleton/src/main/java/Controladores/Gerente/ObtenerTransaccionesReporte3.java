/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.LimitesGerenteModel;
import Modelos.Banco.TransaccionModel;
import Objetos.Banco.LimitesGerente;
import Objetos.Banco.Transaccion;
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
@WebServlet(name = "ObtenerTransaccionesReporte3", urlPatterns = {"/ObtenerTransaccionesReporte3"})
public class ObtenerTransaccionesReporte3 extends HttpServlet {

    TransaccionModel transaccionModel = new TransaccionModel();
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
            Long codigo = Long.parseLong(request.getParameter("codigo"));
            
            ArrayList<Transaccion> listaTransacciones = transaccionModel.obtenerTransacciones(limiteGerente.getLimite_reporte3(), codigo);
            
            request.setAttribute("listaTransacciones", listaTransacciones);
            request.setAttribute("limite", limiteGerente.getLimite_reporte3());
            request.setAttribute("cliente_codigo", codigo);
            request.getRequestDispatcher("/Gerente/Reporte2Transacciones.jsp").forward(request, response);
            
        } catch (IOException | ServletException e) {
            System.out.println("Error al cargar reporte 2 "+e);
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
