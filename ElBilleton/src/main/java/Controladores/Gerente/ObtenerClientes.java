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
@WebServlet("/ObtenerClientes")
public class ObtenerClientes extends HttpServlet {

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
            String opcion = request.getParameter("opcion");
            System.out.println("VISUALIZAR  "+opcion);
            switch (opcion) {
                case "0":
                    request.getRequestDispatcher("/Gerente/CrearCuentaTablaClientes.jsp").forward(request, response);
                    break;
                case "1":
                    request.getRequestDispatcher("/Gerente/Reporte1TablaClientes.jsp").forward(request, response);
                    break;
                case "2":
                    request.setAttribute("opcion", 0);
                    request.getRequestDispatcher("/Gerente/Reporte1TablaClientes.jsp?").forward(request, response);
                    break;
                default:
                    break;
            }

        } catch (NullPointerException | NumberFormatException | IOException | ServletException e) {
            System.out.println("Error buscar cliente: " + e.getMessage());
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
