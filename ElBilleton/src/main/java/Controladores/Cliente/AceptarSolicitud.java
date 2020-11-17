/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.AsociacionModel;
import Modelos.Banco.SolicitudModel;
import Objetos.Banco.Asociacion;
import Objetos.Banco.Solicitud;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet(name = "AceptarSolicitud", urlPatterns = {"/AceptarSolicitud"})
public class AceptarSolicitud extends HttpServlet {

    SolicitudModel solicitudModel = new SolicitudModel();
    AsociacionModel asociacionModel = new AsociacionModel();

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
            Boolean comprobacion = false;
            int solicitud_codigo = Integer.parseInt(request.getParameter("solicitud_codigo"));
            Solicitud solicitud = solicitudModel.obtenerSolicitudCodigo(solicitud_codigo);

            comprobacion = asociacionModel.comprobarAsociacion(solicitud.getCuenta_codigo_envia(), solicitud.getCuenta_codigo_recibe());

            if (comprobacion) {
                solicitud.setEstado("ACEPTADA");
                solicitudModel.actualizarAsociacion(solicitud);
                request.setAttribute("mensaje", 1);
                request.getRequestDispatcher("/Cliente/MensajeError.jsp").forward(request, response);
            } else {
                solicitud.setEstado("ACEPTADA");
                solicitudModel.actualizarAsociacion(solicitud);
                Date fecha = Date.valueOf(LocalDate.now());
                Asociacion asociacion = new Asociacion(0, fecha, solicitud.getCodigo());
                asociacionModel.crearAsociacion(asociacion);
                request.setAttribute("mensaje", 2);
                request.getRequestDispatcher("/Cliente/MensajeExito.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println("Error al aceptar solicitud "+e);
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
