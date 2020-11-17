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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet(name = "RechazarSolicitud", urlPatterns = {"/RechazarSolicitud"})
public class RechazarSolicitud extends HttpServlet {

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
            int solicitud_codigo = Integer.parseInt(request.getParameter("solicitud_codigo"));
            Solicitud solicitud = solicitudModel.obtenerSolicitudCodigo(solicitud_codigo);
            solicitud.setEstado("RECHAZADA");
            solicitudModel.actualizarAsociacion(solicitud);
            request.setAttribute("mensaje", 1);
            request.getRequestDispatcher("/Cliente/MensajeExito.jsp").forward(request, response);
           
            
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println("Error al rechazar solicitud "+e);
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
