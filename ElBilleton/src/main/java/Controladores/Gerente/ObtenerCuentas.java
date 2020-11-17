/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Modelos.Banco.CuentaModel;
import Objetos.Banco.Cuenta;
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
@WebServlet("/ObtenerCuentasGerente")
public class ObtenerCuentas extends HttpServlet {

    CuentaModel cuentaModel = new CuentaModel();

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (request.getSession().getAttribute("user") == null) {
                response.sendRedirect(request.getContextPath() + "/Login");
            }
            
            ArrayList<Cuenta> listaCuenta;
            String codigo = request.getParameter("campo");

            if (codigo == null || (codigo != null && codigo.isEmpty())) {
                listaCuenta = cuentaModel.obtenerCuentasExistentes();
            } else {
                listaCuenta = cuentaModel.obtenerCuentasFiltrando(codigo);
            }

            request.setAttribute("listaCuenta", listaCuenta);
            String opcion = request.getParameter("opcion");
            request.getRequestDispatcher("/Gerente/TablaCuentas.jsp").forward(request, response);

        } catch (NullPointerException | NumberFormatException | IOException | ServletException e) {
            System.out.println("Error buscar cliente: " + e.getMessage());
        }

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
