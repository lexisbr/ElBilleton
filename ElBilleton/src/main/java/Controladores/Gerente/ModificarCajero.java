/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Historial.HistorialCajeroModel;
import Modelos.Usuario.CajeroModel;
import Objetos.Usuarios.Cajero;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
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
@WebServlet("/ModificarCajero")
@MultipartConfig
public class ModificarCajero extends HttpServlet {

    CajeroModel cajeroModel = new CajeroModel();
    HistorialCajeroModel historialCajero = new HistorialCajeroModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            long codigo = Long.parseLong(request.getParameter("codigo"));
            String nombre = request.getParameter("nombre").trim();
            String turno = request.getParameter("turno");
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            String password = request.getParameter("password");

            if (!(nombre.trim().equals("") || direccion.trim().equals(""))) {
                Cajero cajero = new Cajero(codigo, nombre, turno, dpi, direccion, sexo, password);
                cajeroModel.modificarCajero(cajero);
                historialCajero.agregarHistorialCajero(cajero);

                request.setAttribute("cajero_codigo", codigo);
                request.setAttribute("exito", 1);
                request.getRequestDispatcher("Gerente/ExitoModificarCajero.jsp").forward(request, response);
            } else {
                request.setAttribute("exito", 1);
                request.getRequestDispatcher("Gerente/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (UnsupportedEncodingException | NumberFormatException | SQLException e) {
            System.out.println("Error en controlador al actualizar cliente " + e);
        }

    }

}
