/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Historial.HistorialGerenteModel;
import Modelos.Usuario.ClienteModel;
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
            
            Cliente cliente = new Cliente(0,nombre,dpi,direccion,sexo,Date.valueOf(fecha_nacimiento),pdfDpi,password);
            
            long codigoGenerado = clienteModel.agregarCliente(cliente);
            cliente.setCodigo(codigoGenerado);
            
            HistorialClienteModel historialModel = new HistorialClienteModel();
            historialModel.agregarHistorialCliente(cliente);
            
            request.setAttribute("clienteCreado", codigoGenerado);
            request.getRequestDispatcher("Gerente/ExitoCrearCliente.jsp").forward(request, response);
            
            
        } catch (SQLException e) {
            System.out.println("Error al agregar cliente "+e);
        }
        

    }

}
