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
            if (request.getSession().getAttribute("usuario") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            
            ArrayList<Cajero> listaCajeros;
            String codigo = request.getParameter("campo");

            if (codigo == null || (codigo != null && codigo.isEmpty())) {
                listaCajeros = cajeroModel.obtenerCajeros();
            } else {
                listaCajeros = cajeroModel.obtenerCajerosFiltrando(Long.parseLong(codigo));
            }

            request.setAttribute("listaCajeros", listaCajeros);
            String opcion = request.getParameter("opcion");

            switch (opcion) {
                case "0":
                    request.setAttribute("opcion", 0);
                    request.getRequestDispatcher("/Gerente/ActualizarTablaCajeros.jsp").forward(request, response);
                    break;
                case "1":
                    request.setAttribute("opcion", 1);
                    request.getRequestDispatcher("/Gerente/ActualizarTablaCajeros.jsp").forward(request, response);
                    break;
                case "2":
                    request.setAttribute("opcion", 2);
                    request.getRequestDispatcher("/Gerente/ActualizarTablaCajeros.jsp").forward(request, response);
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
