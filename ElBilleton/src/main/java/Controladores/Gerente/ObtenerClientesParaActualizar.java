/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cliente;
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
@WebServlet("/ObtenerClientesParaActualizar")
public class ObtenerClientesParaActualizar extends HttpServlet {

    ClienteModel clienteModel = new ClienteModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            ArrayList<Cliente> listaClientes;
            String codigo = request.getParameter("campo");

            if (codigo == null || (codigo != null && codigo.isEmpty())) {
                listaClientes = clienteModel.obtenerClientes();
            } else {
                listaClientes = clienteModel.obtenerClientesFiltrando(Long.parseLong(codigo));
            }

            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("opcion", 1);
            request.getRequestDispatcher("/Gerente/ActualizarTablaClientes.jsp").forward(request, response);

        } catch (NullPointerException | NumberFormatException | IOException | ServletException e) {
            System.out.println("Error buscar cliente: " + e.getMessage());
        }

    }

}
