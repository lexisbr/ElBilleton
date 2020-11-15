/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Modelos.Historial.HistorialCajeroModel;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Usuario.CajeroModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Cliente;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
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
@WebServlet("/CrearCajero")
public class CrearCajero extends HttpServlet {

    CajeroModel cajeroModel = new CajeroModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre").trim();
            String turno = request.getParameter("turno");
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion").trim();
            String sexo = request.getParameter("sexo");
            String password = request.getParameter("password");

            if (!(nombre.trim().equals("") || direccion.trim().equals(""))) {

                Cajero cajero = new Cajero(0, nombre, turno, dpi, direccion, sexo, password);

                long codigoGenerado = cajeroModel.agregarCajero(cajero);
                cajero.setCodigo(codigoGenerado);

                HistorialCajeroModel historialModel = new HistorialCajeroModel();
                historialModel.agregarHistorialCajero(cajero);

                request.setAttribute("cajeroCreado", codigoGenerado);
                request.getRequestDispatcher("Gerente/ExitoCrearCajero.jsp").forward(request, response);
                
            } else {
                request.setAttribute("exito", 1);
                request.getRequestDispatcher("Gerente/EstadoInactivo.jsp").forward(request, response);
            }

        } catch (SQLException e) {
            System.out.println("Error al agregar cajero " + e);
        }
    }

}
