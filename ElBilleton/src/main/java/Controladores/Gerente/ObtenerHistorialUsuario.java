/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Historial.HistorialCajeroModel;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Historial.HistorialGerenteModel;
import Objetos.Historiales.HistorialCajero;
import Objetos.Historiales.HistorialCliente;
import Objetos.Historiales.HistorialGerente;
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
@WebServlet(name = "ObtenerHistorialUsuario", urlPatterns = {"/ObtenerHistorialUsuario"})
public class ObtenerHistorialUsuario extends HttpServlet {

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
            String tipo = request.getParameter("tipo");
            Long codigo = Long.parseLong(request.getParameter("codigo"));

            switch (tipo) {
                case "cliente":
                    HistorialClienteModel historialCliente = new HistorialClienteModel();

                    ArrayList<HistorialCliente> listaHistorialCliente = historialCliente.obtenerHistorialCliente(codigo);

                    request.setAttribute("listaHistorial", listaHistorialCliente);
                    request.setAttribute("cliente_codigo", codigo.toString());
                    request.getRequestDispatcher("/Gerente/Reporte1Cliente.jsp").forward(request, response);
                    break;
                case "cajero":
                    HistorialCajeroModel historialCajero = new HistorialCajeroModel();

                    ArrayList<HistorialCajero> listaHistorialCajero = historialCajero.obtenerHistorial(codigo);
                    
                    request.setAttribute("listaHistorial", listaHistorialCajero);
                    request.setAttribute("cajero_codigo", codigo.toString());
                    request.getRequestDispatcher("/Gerente/Reporte1Cajero.jsp").forward(request, response);
                    break;
                case "gerente":
                    HistorialGerenteModel historialGerente = new HistorialGerenteModel();
                    
                    ArrayList<HistorialGerente> listaHistorialGerente = historialGerente.obtenerHistorial(codigo);
                    
                    request.setAttribute("listaHistorial", listaHistorialGerente);
                    request.setAttribute("gerente_codigo", codigo.toString());
                    request.getRequestDispatcher("/Gerente/Reporte1Gerente.jsp").forward(request, response);
                    break;
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("Error al cargar el historial en servlet " + e);
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
