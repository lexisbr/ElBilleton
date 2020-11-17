/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cajero;

import Controladores.Cliente.*;
import Modelos.Banco.CuentaModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Usuarios.Cliente;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
@WebServlet(name = "ObtenerCuentaRetiro", urlPatterns = {"/ObtenerCuentaRetiro"})
public class ObtenerCuentaRetiro extends HttpServlet {

    CuentaModel cuentaModel = new CuentaModel();
    ClienteModel clienteModel = new ClienteModel();

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
            long codigo = Long.parseLong(request.getParameter("cuenta_codigo"));

            Cuenta cuenta = cuentaModel.obtenerCuenta(codigo);
            Cliente cliente = clienteModel.obtenerCliente(cuenta.getCliente_codigo());

            request.setAttribute("cuenta", cuenta);
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("/Cajero/RealizarRetiro.jsp").forward(request, response);

        } catch (NullPointerException | UnsupportedEncodingException | SQLException | NumberFormatException e) {
            System.out.println("Error al obtener cuenta servlet " + e);
            request.setAttribute("exito", 0);
            request.getRequestDispatcher("/Cajero/BuscarCuentaDeposito.jsp").forward(request, response);
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
