/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.LimitesGerenteModel;
import Objetos.Banco.LimitesGerente;
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
@WebServlet("/CargarLimites")
public class CargarLimites extends HttpServlet {

    LimitesGerenteModel limiteModel = new LimitesGerenteModel();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LimitesGerente limites = (LimitesGerente) limiteModel.obtenerLimites();

            request.setAttribute("limites", limites);

            request.getRequestDispatcher("Gerente/EstablecerLimites.jsp").forward(request, response);

        } catch (UnsupportedEncodingException | NumberFormatException e) {
            System.out.println("Error al cargar limites en servlet " + e);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LimitesGerente limites = (LimitesGerente) limiteModel.obtenerLimites();
            request.setAttribute("exito", 2);
            request.setAttribute("limites", limites);

            request.getRequestDispatcher("Gerente/EstablecerLimites.jsp").forward(request, response);

        } catch (UnsupportedEncodingException | NumberFormatException e) {
            System.out.println("Error al cargar limites en servlet " + e);
        }

    }

}
