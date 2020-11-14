/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.CajeroModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cajero;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author lex
 */
@WebServlet("/ObtenerCajeros")
public class ObtenerCajeros extends HttpServlet {

    CajeroModel cajeroModel = new CajeroModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            ArrayList<Cajero> listaCajeros; 
            String codigo = request.getParameter("campo");
            
            if(codigo == null || (codigo != null && codigo.isEmpty())){
                listaCajeros = cajeroModel.obtenerCajeros();
            }else{
                listaCajeros = cajeroModel.obtenerCajerosFiltrando(Long.parseLong(codigo));
            }
            
            request.setAttribute("listaCajeros", listaCajeros);
                
            request.getRequestDispatcher("/Gerente/ActualizarTablaCajeros.jsp").forward(request, response);

        } catch (NullPointerException | NumberFormatException |IOException | ServletException e) {
            System.out.println("Error buscar cliente: " + e.getMessage());
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}
