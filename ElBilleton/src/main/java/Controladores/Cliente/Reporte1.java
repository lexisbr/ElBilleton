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
@WebServlet(name = "Reporte1", urlPatterns = {"/Reporte1"})
public class Reporte1 extends HttpServlet {


    TransaccionModel transaccionModel = new TransaccionModel();
    CuentaModel cuentaModel = new CuentaModel();
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
            long cuenta_codigo = Long.parseLong(request.getParameter("cuenta_codigo"));
            ArrayList<Transaccion> listaTransacciones = transaccionModel.obtener15TransaccionesCuenta(cuenta_codigo);
            Cuenta cuenta = cuentaModel.obtenerCuenta(cuenta_codigo);
            
            request.setAttribute("listaTransacciones", listaTransacciones);
            request.setAttribute("cuenta", cuenta);
            request.getRequestDispatcher("/Cliente/Reporte1Transacciones.jsp").forward(request, response);
            
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
