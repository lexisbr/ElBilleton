/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.CuentaModel;
import Modelos.Banco.TransaccionModel;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import java.io.IOException;
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
@WebServlet(name = "Reporte3Transacciones", urlPatterns = {"/Reporte3Transacciones"})
public class Reporte3Transacciones extends HttpServlet {


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
            long cuenta_codigo = Long.parseLong(request.getParameter("codigo"));
            String fecha1 = request.getParameter("fecha1");
            String fecha2 = request.getParameter("fecha2");
            
            
            ArrayList<Transaccion> listaTransaccion = transaccionModel.enIntervaloCliente(cuenta_codigo, Date.valueOf(fecha1), Date.valueOf(fecha2));
            
            request.setAttribute("listaTransaccion", listaTransaccion);
            request.setAttribute("fecha1", fecha1);
            request.setAttribute("fecha2", fecha2);
            request.setAttribute("codigo", cuenta_codigo);
            request.getRequestDispatcher("/Cliente/Reporte3Transacciones.jsp").forward(request, response);
            
        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("Error al cargar reporte 1 "+e);
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
