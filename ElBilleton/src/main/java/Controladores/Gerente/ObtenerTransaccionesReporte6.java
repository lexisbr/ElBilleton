/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.TransaccionModel;
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
@WebServlet(name = "ObtenerTransaccionesReporte6", urlPatterns = {"/ObtenerTransaccionesReporte6"})
public class ObtenerTransaccionesReporte6 extends HttpServlet {

    TransaccionModel transaccionModel = new TransaccionModel();
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
            long codigo = Long.parseLong(request.getParameter("cliente_codigo"));
            
            ArrayList<Transaccion> listaTransacciones = transaccionModel.obtenerTransacciones(codigo);
            
            request.setAttribute("listaTransacciones", listaTransacciones);
            request.setAttribute("cliente_codigo", codigo);
            request.setAttribute("opcion", 0);
            
            request.getRequestDispatcher("/Gerente/Reporte2Transacciones.jsp").forward(request, response);
            
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("Error al obtener transacciones en reporte 6 "+e);
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
