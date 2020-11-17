/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.SolicitudModel;
import Objetos.Banco.Solicitud;
import java.io.IOException;
import java.sql.SQLException;
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
@WebServlet(name = "ObtenerSolicitudesPendientes", urlPatterns = {"/ObtenerSolicitudesPendientes"})
public class ObtenerSolicitudesPendientes extends HttpServlet {

     SolicitudModel solicitudModel = new SolicitudModel();

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
            long cuenta_codigo = Long.valueOf(request.getParameter("cuenta_codigo"));
            ArrayList<Solicitud> listaSolicitudes= solicitudModel.obtenerSolicitudesPendientes(cuenta_codigo);
            
            request.setAttribute("listaSolicitudes", listaSolicitudes);
            
            request.getRequestDispatcher("/Cliente/TablaSolicitudes.jsp").forward(request, response);
            
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println("Error al obtener solicitudes "+e);
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
