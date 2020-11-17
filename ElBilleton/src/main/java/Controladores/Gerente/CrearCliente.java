/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Clases.DuplicarPDF;
import Modelos.Banco.CuentaModel;
import Modelos.Banco.TransaccionModel;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import Objetos.Usuarios.Cliente;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
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
    DuplicarPDF crearPDF;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            CrearArchivo obtenerPdf = new CrearArchivo();
            String nombre = request.getParameter("nombre").trim();
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            String fecha_nacimiento = request.getParameter("fecha");
            String password = request.getParameter("password");
            InputStream pdfDpi = obtenerPdf.obtenerArchivo("pdfDpi", request);

            String monto = request.getParameter("monto");
            String fecha = request.getParameter("fecha");
            if (!(nombre.trim().equals("") || direccion.trim().equals(""))) {
                crearPDF = new DuplicarPDF(pdfDpi);

                InputStream pdf1 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
                InputStream pdf2 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());

                Cliente cliente = new Cliente(0, nombre, dpi, direccion, sexo, Date.valueOf(fecha_nacimiento), pdf1, password);
                long codigoGeneradoCliente = clienteModel.agregarCliente(cliente);
                cliente.setCodigo(codigoGeneradoCliente);
                cliente.setPdfDPI(pdf2);

                HistorialClienteModel historialModel = new HistorialClienteModel();
                historialModel.agregarHistorialCliente(cliente);

                Cuenta cuenta = new Cuenta(0, Date.valueOf(fecha), Double.parseDouble(monto), codigoGeneradoCliente);

                long codigoGeneradoCuenta = cuentaModel.agregarCuenta(cuenta);
                

                request.setAttribute("cuenta_codigo", codigoGeneradoCuenta);
                request.setAttribute("cliente_codigo", codigoGeneradoCliente);

                request.getRequestDispatcher("Gerente/ExitoCrearCliente.jsp").forward(request, response);
            } else {
                request.setAttribute("exito", 1);
                request.getRequestDispatcher("Gerente/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (IOException | NumberFormatException | SQLException | ServletException e) {
            System.out.println("Error al agregar cliente " + e);
        }

    }

}
