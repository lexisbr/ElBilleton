/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.CuentaModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Usuarios.Cliente;
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
@WebServlet(name = "CargarDatosTransferencia", urlPatterns = {"/CargarDatosTransferencia"})
public class CargarDatosTransferencia extends HttpServlet {

    CuentaModel cuentaModel = new CuentaModel();
    ClienteModel clienteModel = new ClienteModel();

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
            long cuenta_credito = Long.parseLong(request.getParameter("cuenta_credito"));
            long cuenta_debito = Long.parseLong(request.getParameter("cuenta_debito"));
            long cliente_codigo = Long.parseLong(request.getSession().getAttribute("user").toString());

            Cuenta cuenta_recibe = cuentaModel.obtenerCuenta(cuenta_credito);
            Cuenta cuenta_envia = cuentaModel.obtenerCuenta(cuenta_debito);
            
            Cliente cliente_actual = clienteModel.obtenerCliente(cliente_codigo);
            Cliente cliente_seleccionado = clienteModel.obtenerCliente(cuenta_recibe.getCliente_codigo());
            
            request.setAttribute("cuenta_credito", cuenta_recibe);
            request.setAttribute("cuenta_debito", cuenta_envia);
            request.setAttribute("cliente_actual", cliente_actual);
            request.setAttribute("cliente_seleccionado", cliente_seleccionado);
            
            request.getRequestDispatcher("/Cliente/Transferencia.jsp").forward(request, response);
            
            
        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println("Error al cargar datos para transferencia");
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
