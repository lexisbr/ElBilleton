/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.CuentaModel;
import Modelos.Banco.TransaccionModel;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
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
@WebServlet("/CrearCuenta")
public class CrearCuenta extends HttpServlet {
    
    CuentaModel cuentaModel = new CuentaModel();
    TransaccionModel transaccionModel = new TransaccionModel();
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String fecha_creacion = request.getParameter("fecha");
            String monto = request.getParameter("monto");
            String cliente_codigo = request.getParameter("cliente_codigo");
            Date fecha_convertida = Date.valueOf(fecha_creacion);
            
            Cuenta cuenta = new Cuenta(0,fecha_convertida,Double.parseDouble(monto), Long.parseLong(cliente_codigo));
            
            long codigoGenerado = cuentaModel.agregarCuenta(cuenta);
            
            Transaccion transaccion = new Transaccion(0, fecha_convertida, Time.valueOf(LocalTime.now()), "CREDITO", Double.parseDouble(monto), codigoGenerado, 101);
            transaccionModel.agregarTransaccion(transaccion);
            
            request.setAttribute("cuentaCreada", codigoGenerado);
            request.getRequestDispatcher("Gerente/ExitoCrearCuenta.jsp").forward(request, response);
            
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Error al agregar cuenta "+e);
        }
    }
    
    
}
