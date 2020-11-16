/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.CuentaModel;
import Objetos.Banco.Cuenta;
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
@WebServlet(name = "ObtenerCuentas", urlPatterns = {"/ObtenerCuentas"})
public class ObtenerCuentas extends HttpServlet {

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
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }

            Long cliente_codigo = Long.parseLong(request.getSession().getAttribute("user").toString());
            String cuenta_codigo = request.getParameter("campo");
            ArrayList<Cuenta> listaCuentas;

            if (cuenta_codigo == null || (cuenta_codigo != null && cuenta_codigo.isEmpty())) {
                listaCuentas = cuentaModel.obtenerCuentas(cliente_codigo);
            } else {
                listaCuentas = cuentaModel.obtenerCuentasFiltrando(cliente_codigo, cuenta_codigo);
            }
            request.setAttribute("listaCuentas", listaCuentas);
            request.getRequestDispatcher("/Cliente/TablaCuentas.jsp").forward(request, response);

        } catch (IOException | NumberFormatException | ServletException e) {
            System.out.println("Error al obtener cuentas del cliente " + e);
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
