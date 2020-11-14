/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.CajeroModel;
import Objetos.Usuarios.Cajero;
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
@WebServlet("/ObtenerCajero")
public class ObtenerCajero extends HttpServlet{
    CajeroModel cajeroModel = new CajeroModel();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String codigo = request.getParameter("cajero_codigo");
        try {
            Cajero cajero =  (Cajero) cajeroModel.obtenerCajero(Long.parseLong(codigo));
            
            request.setAttribute("cajero", cajero);
            
            request.getRequestDispatcher("Gerente/ActualizarCajero.jsp").forward(request, response);
            
            
        } catch (UnsupportedEncodingException | NumberFormatException | SQLException e) {
        }
    
    }
    
}
