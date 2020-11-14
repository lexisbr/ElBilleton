/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cliente;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
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
public class ModificarCliente extends HttpServlet{
    
    ClienteModel clienteModel = new ClienteModel();
    HistorialClienteModel historialCliente = new HistorialClienteModel();
    
     @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
         try {
             Cliente cliente;
             CrearArchivo obtenerPdf = new CrearArchivo();
            long codigo = Long.parseLong(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre");
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion");
            String sexo = request.getParameter("sexo");
            Date fecha_nacimiento = Date.valueOf(request.getParameter("fecha"));
            String password = request.getParameter("password");
            
            Part archivo = request.getPart("file");
            
            if(archivo!=null&&archivo.getSize()>0){
                InputStream pdfDpi = obtenerPdf.obtenerArchivo("file", request);
                cliente = new Cliente(codigo, nombre, dpi, direccion, sexo, fecha_nacimiento, pdfDpi, password);
            }else{
                cliente = new Cliente(codigo, nombre, dpi, direccion, sexo, fecha_nacimiento, clienteModel.obtenerDPI(codigo), password);
            }
            
            clienteModel.modificarCliente(cliente);
            historialCliente.agregarHistorialCliente(cliente);
            request.setAttribute("cliente_codigo", codigo);
            request.getRequestDispatcher("Gerente/ExitoModificarCliente.jsp").forward(request, response);
             
             
         } catch (UnsupportedEncodingException | NumberFormatException | SQLException e) {
             System.out.println("Error en controlador al actualizar cliente "+e);
         }
    
    }
    
}
