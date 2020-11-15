/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Clases.DuplicarPDF;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cliente;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author lex
 */
@WebServlet("/ModificarCliente")
@MultipartConfig
public class ModificarCliente extends HttpServlet {

    ClienteModel clienteModel = new ClienteModel();
    HistorialClienteModel historialCliente = new HistorialClienteModel();
    DuplicarPDF crearPDF;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Cliente cliente;
            CrearArchivo obtenerPdf = new CrearArchivo();
            long codigo = Long.parseLong(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre").trim();
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            Date fecha_nacimiento = Date.valueOf(request.getParameter("fecha"));
            String password = request.getParameter("password");

            Part archivo = request.getPart("file");
            if (!(nombre.trim().equals("") || direccion.trim().equals(""))) {

                if (archivo != null && archivo.getSize() > 0) {
                    InputStream pdfDpi = obtenerPdf.obtenerArchivo("file", request);
                    crearPDF = new DuplicarPDF(pdfDpi);

                    InputStream pdf1 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
                    InputStream pdf2 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());

                    cliente = new Cliente(codigo, nombre, dpi, direccion, sexo, fecha_nacimiento, pdf1, password);
                    clienteModel.modificarCliente(cliente);
                    cliente.setPdfDPI(pdf2);
                } else {
                    InputStream pdfDpi = clienteModel.obtenerDPI(codigo);
                    crearPDF = new DuplicarPDF(pdfDpi);

                    InputStream pdf1 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
                    InputStream pdf2 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());

                    cliente = new Cliente(codigo, nombre, dpi, direccion, sexo, fecha_nacimiento, pdf1, password);
                    clienteModel.modificarCliente(cliente);
                    cliente.setPdfDPI(pdf2);
                }

                historialCliente.agregarHistorialCliente(cliente);
                request.setAttribute("cliente_codigo", codigo);
                request.getRequestDispatcher("Gerente/ExitoModificarCliente.jsp").forward(request, response);
            } else {
                request.setAttribute("exito", 1);
                request.getRequestDispatcher("Gerente/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (UnsupportedEncodingException | NumberFormatException | SQLException e) {
            System.out.println("Error en controlador al actualizar cliente " + e);
        }

    }

}
