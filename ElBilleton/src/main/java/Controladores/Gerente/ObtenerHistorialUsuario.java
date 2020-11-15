/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Historial.HistorialClienteModel;
import Objetos.Historiales.HistorialCliente;
import java.io.IOException;
import java.io.PrintWriter;
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

                    ArrayList<HistorialCliente> listaHistorial = historialCliente.obtenerHistorialCliente(codigo);

                    request.setAttribute("listaHistorial", listaHistorial);
                    request.getRequestDispatcher("/Gerente/Reporte1Cliente.jsp").forward(request, response);

                    break;
                case "cajero":
                    break;
                case "gerente":
                    break;
            }
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("Error al cargar el historial en servlet "+e);
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
