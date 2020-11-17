package Controladores.Cliente;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import Modelos.Banco.SolicitudModel;
import Objetos.Banco.Solicitud;
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
@WebServlet(name = "Reporte5Solicitudes", urlPatterns = {"/Reporte5Solicitudes"})
public class Reporte5Solicitudes extends HttpServlet {

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
            long cliente_codigo = Long.parseLong(request.getSession().getAttribute("user").toString());
            ArrayList<Solicitud> listaSolicitudes = solicitudModel.obtenerSolicitudesEnviadas(cliente_codigo);
            
            request.setAttribute("listaSolicitudes", listaSolicitudes);
            request.setAttribute("cliente_codigo", cliente_codigo);
            request.getRequestDispatcher("/Cliente/Reporte5Enviadas.jsp").forward(request, response);
               
            
        } catch (Exception e) {
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
