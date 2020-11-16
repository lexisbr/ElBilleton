/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.SolicitudModel;
import Objetos.Banco.Solicitud;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet(name = "CrearSolicitud", urlPatterns = {"/CrearSolicitud"})
public class CrearSolicitud extends HttpServlet {

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
            long codigo_envia = Long.parseLong(request.getParameter("cuenta_envia"));
            long codigo_recibe = Long.parseLong(request.getParameter("cuenta_recibe"));
            int cuenta = solicitudModel.obtenerCount(codigo_envia, codigo_recibe, "PENDIENTE");
              System.out.println("CUENTA "+cuenta);
            if(cuenta<3){
                Solicitud solicitud = new Solicitud(0,Date.valueOf(LocalDate.now()),"PENDIENTE",codigo_envia,codigo_recibe);
                solicitudModel.agregarSolicitud(solicitud);
                request.setAttribute("mensaje", 0);
                request.getRequestDispatcher("/Cliente/MensajeExito.jsp").forward(request, response);
            }else{
                request.setAttribute("mensaje", 0);
                request.getRequestDispatcher("/Cliente/MensajeError.jsp").forward(request, response);
                
            }
            
            
        } catch (IOException | SQLException | NumberFormatException | ServletException e) {
            System.out.println("Error al procesar solicitud "+e);
        } 
    } 

}
