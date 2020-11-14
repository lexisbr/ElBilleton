/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Modelos.Historial.HistorialCajeroModel;
import Modelos.Historial.HistorialClienteModel;
import Modelos.Historial.HistorialGerenteModel;
import Modelos.Usuario.CajeroModel;
import Modelos.Usuario.ClienteModel;
import Modelos.Usuario.GerenteModel;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Cliente;
import Objetos.Usuarios.Gerente;
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
@WebServlet("/CrearGerente")
public class CrearGerente extends HttpServlet {
    
    GerenteModel gerenteModel = new GerenteModel();
    HistorialGerenteModel historialModel = new HistorialGerenteModel();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            String turno = request.getParameter("turno");
            String dpi = request.getParameter("dpi");
            String direccion = request.getParameter("direccion");
            String sexo = request.getParameter("sexo");
            String password = request.getParameter("password");
            
            Gerente gerente = new Gerente(0,nombre,turno,dpi,direccion,sexo,password);
            
            long codigoGenerado = gerenteModel.agregarGerente(gerente);
            gerente.setCodigo(codigoGenerado);
            
            historialModel.agregarHistorialGerente(gerente);
            
            request.setAttribute("gerenteCreado", codigoGenerado);
            request.getRequestDispatcher("Gerente/ExitoCrearGerente.jsp").forward(request, response);
            
            
        } catch (SQLException e) {
            System.out.println("Error al agregar cajero "+e);
        }
    }
    
}
