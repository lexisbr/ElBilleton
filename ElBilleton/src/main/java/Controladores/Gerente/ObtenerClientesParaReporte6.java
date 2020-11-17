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
@WebServlet("/ObtenerClientesParaReporte6")
public class ObtenerClientesParaReporte6 extends HttpServlet {

    ClienteModel clienteModel = new ClienteModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ArrayList<Cliente> listaClientes = null;
            String campo = request.getParameter("campo");
            String filtro = request.getParameter("filtro");

            if (campo == null || (campo != null && campo.isEmpty())) {
                listaClientes = clienteModel.obtenerClientes();
            } else {
                switch (filtro) {
                    case "NOMBRE":
                        listaClientes = clienteModel.obtenerClientesFiltrandoNombre(campo);
                        request.setAttribute("listaClientes", listaClientes);
                        break;
                    case "DINERO":
                        listaClientes = clienteModel.obtenerClientesFiltrandoMonto(campo);
                        request.setAttribute("listaClientes", listaClientes);
                        break;
                }

            }

            request.setAttribute("listaClientes", listaClientes);
            request.setAttribute("opcion", 0);
            request.getRequestDispatcher("/Gerente/TablaClientesReporte6.jsp").forward(request, response);

        } catch (NullPointerException | NumberFormatException | IOException | ServletException e) {
            System.out.println("Error buscar cliente: " + e.getMessage());
        }

    }

}
