/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.CuentaModel;
import Objetos.Banco.Cuenta;
import java.awt.geom.CubicCurve2D;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
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
            
            request.setAttribute("cuentaCreada", codigoGenerado);
            request.getRequestDispatcher("Gerente/ExitoCrearCuenta.jsp").forward(request, response);
            
            
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Error al agregar cuenta "+e);
        }
    }
    
    
}
