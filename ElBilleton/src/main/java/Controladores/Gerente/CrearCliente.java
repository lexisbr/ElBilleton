/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Modelos.Banco.CuentaModel;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Historial.HistorialGerenteModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Usuarios.Cliente;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet("/CrearCliente")
@MultipartConfig
public class CrearCliente extends HttpServlet {

    ClienteModel clienteModel = new ClienteModel();
    CuentaModel cuentaModel = new CuentaModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            CrearArchivo obtenerPdf = new CrearArchivo();
            String nombre = request.getParameter("nombre");
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion");
            String sexo = request.getParameter("sexo");
            String fecha_nacimiento = request.getParameter("fecha");
            String password = request.getParameter("password");
            InputStream pdfDpi = obtenerPdf.obtenerArchivo("pdfDpi", request);

            String monto = request.getParameter("monto");
            String fecha = request.getParameter("fecha");
            
            Cliente cliente = new Cliente(0, nombre, dpi, direccion, sexo, Date.valueOf(fecha_nacimiento), pdfDpi, password);
            long codigoGeneradoCliente = clienteModel.agregarCliente(cliente);
            cliente.setCodigo(codigoGeneradoCliente);
            HistorialClienteModel historialModel = new HistorialClienteModel();
            historialModel.agregarHistorialCliente(cliente);

            Cuenta cuenta = new Cuenta(0, Date.valueOf(fecha), Double.parseDouble(monto), codigoGeneradoCliente);
            
            long codigoGeneradoCuenta = cuentaModel.agregarCuenta(cuenta);
            
            request.setAttribute("cuenta_codigo", codigoGeneradoCuenta);
            request.setAttribute("cliente_codigo", codigoGeneradoCliente);
            
            request.getRequestDispatcher("Gerente/ExitoCrearCliente.jsp").forward(request, response);

        } catch (Exception e) {
            System.out.println("Error al agregar cliente " + e);
        }

    }

}
