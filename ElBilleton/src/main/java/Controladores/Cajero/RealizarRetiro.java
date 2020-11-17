/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cajero;

import Modelos.Banco.CuentaModel;
import Modelos.Banco.TransaccionModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import Objetos.Usuarios.Cliente;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet(name = "RealizarRetiro", urlPatterns = {"/RealizarRetiro"})
public class RealizarRetiro extends HttpServlet {

    TransaccionModel transaccionModel = new TransaccionModel();
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
            long cuenta_codigo = Long.parseLong(request.getParameter("cuenta_codigo"));
            long cajero_codigo = Long.parseLong(request.getSession().getAttribute("user").toString());
            double monto_actual = Double.parseDouble(request.getParameter("monto_actual"));
            double monto_retirar = Double.parseDouble(request.getParameter("monto_retirar"));

            if (monto_actual >= monto_retirar) {
                Transaccion transaccion = new Transaccion(0, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "DEBITO", monto_retirar, cuenta_codigo, cajero_codigo);
                long codigo_generado = transaccionModel.agregarTransaccion(transaccion);

                Cuenta cuenta = cuentaModel.obtenerCuenta(cuenta_codigo);
                cuenta.setMonto(cuenta.getMonto() - monto_retirar);
                cuentaModel.modificarMonto(cuenta);

                request.setAttribute("transaccion_codigo", codigo_generado);
                request.setAttribute("monto", monto_retirar);
                request.setAttribute("mensaje", 1);
                request.setAttribute("recargar", 0);
                request.getRequestDispatcher("Cajero/MensajeExito.jsp").forward(request, response);

            } else {
                request.setAttribute("mensaje", 0);
                Cuenta cuenta = cuentaModel.obtenerCuenta(cuenta_codigo);
                Cliente cliente = clienteModel.obtenerCliente(cuenta.getCliente_codigo());
                request.setAttribute("cuenta", cuenta);
                request.setAttribute("cliente", cliente);
                request.getRequestDispatcher("/Cajero/RealizarRetiro.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println("Error al crear retiro "+e);
        }
    }

}
