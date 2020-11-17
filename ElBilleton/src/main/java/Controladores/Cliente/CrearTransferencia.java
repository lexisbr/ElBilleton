/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

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
@WebServlet(name = "CrearTransferencia", urlPatterns = {"/CrearTransferencia"})
public class CrearTransferencia extends HttpServlet {
    CuentaModel cuentaModel = new CuentaModel();
    TransaccionModel transaccionModel = new TransaccionModel();
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
            String monto = request.getParameter("monto_transferido");
            Double dinero = Double.parseDouble(monto);
            String cuenta_credito = request.getParameter("cuenta_credito");
            String cuenta_debito = request.getParameter("cuenta_debito");
            Cuenta cuenta_deposito = cuentaModel.obtenerCuenta(Long.parseLong(cuenta_credito));
            Cuenta cuenta_retiro = cuentaModel.obtenerCuenta(Long.parseLong(cuenta_debito));
            
            
            if (cuenta_retiro.getMonto() < dinero) {
                Cliente cliente_actual = clienteModel.obtenerCliente(cuenta_retiro.getCliente_codigo());
                Cliente cliente_seleccionado = clienteModel.obtenerCliente(cuenta_deposito.getCliente_codigo());
                request.setAttribute("mensaje", 0);
                request.setAttribute("cuenta_credito", cuenta_deposito);
                request.setAttribute("cuenta_debito", cuenta_retiro);
                request.setAttribute("cliente_actual", cliente_actual);
                request.setAttribute("cliente_seleccionado", cliente_seleccionado);
                System.out.println("Er");
                request.getRequestDispatcher("/Cliente/Transferencia.jsp").forward(request, response);
            } else {
                
                Transaccion transaccion_debito = new Transaccion(0, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "DEBITO", dinero, cuenta_retiro.getCodigo(), 101);
                transaccionModel.agregarTransaccion(transaccion_debito);
                Transaccion transaccion_retiro = new Transaccion(0, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "CREDITO", dinero, cuenta_deposito.getCodigo(), 101);
                transaccionModel.agregarTransaccion(transaccion_retiro);
                
                cuenta_deposito.setMonto(dinero + cuenta_deposito.getMonto());
                dinero = cuenta_retiro.getMonto() - dinero;
                cuenta_retiro.setMonto(dinero);
                cuentaModel.modificarMonto(cuenta_deposito);
                cuentaModel.modificarMonto(cuenta_retiro);
                request.setAttribute("mensaje", 3);
                request.getRequestDispatcher("/Cliente/MensajeExito.jsp").forward(request, response);
            }
        } catch (SQLException | IOException | NumberFormatException | ServletException e) {
            System.out.println("Error al crear transferencia " + e);
        }

    }

}
