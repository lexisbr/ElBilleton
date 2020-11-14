/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cliente;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
@WebServlet("/ObtenerCliente")
public class ObtenerCliente extends HttpServlet{
    ClienteModel clienteModel = new ClienteModel();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("cliente_codigo");
        try {
            Cliente cliente =  (Cliente) clienteModel.obtenerCliente(Long.parseLong(codigo));
            
            request.setAttribute("cliente", cliente);
            
            request.getRequestDispatcher("Gerente/ActualizarCliente.jsp").forward(request, response);
            
            
        } catch (UnsupportedEncodingException | NumberFormatException | SQLException e) {
        }
    
    }
    
}
