/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
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
@WebServlet(name = "CargarReporteIntervalo", urlPatterns = {"/CargarReporteIntervalo"})
public class CargarReporteIntervalo extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String opcion = request.getParameter("opcion");
            Date fecha1 = Date.valueOf(request.getParameter("fecha1"));
            Date fecha2 = Date.valueOf(request.getParameter("fecha2"));

            if (fecha1.before(fecha2)) {
                switch (opcion) {
                    case "0":
                        ClienteModel clienteModel = new ClienteModel();

                        ArrayList<Cliente> listaClientes = clienteModel.sinTransacciones(fecha1, fecha2);

                        request.setAttribute("listaClientes", listaClientes);

                        request.getRequestDispatcher("/Gerente/Reporte5Clientes.jsp").forward(request, response);

                        break;
                    case "1":
                        break;

                }
            } else {
                request.setAttribute("exito", 2);
                request.getRequestDispatcher("/Gerente/IntervaloReporte.jsp").forward(request, response);
            }

        } catch (Exception e) {
            System.out.println("Error al cargar reporte 5 " + e);
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
