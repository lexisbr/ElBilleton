/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Cliente;

import Modelos.Banco.AsociacionModel;
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
@WebServlet(name = "CargarCuentasTransaccion", urlPatterns = {"/CargarCuentasTransaccion"})
public class CargarCuentasTransaccion extends HttpServlet {

    CuentaModel cuentaModel = new CuentaModel();
    AsociacionModel asociacionModel = new AsociacionModel();
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            long cliente_codigo = Long.parseLong(request.getSession().getAttribute("user").toString());
            long cuenta_codigo = Long.parseLong(request.getParameter("cuenta_codigo"));
            Cuenta cuenta_seleccionada = cuentaModel.obtenerCuenta(cuenta_codigo);
            
            ArrayList asociacionesEnvia = asociacionModel.obtenerAsociacionesEnvia(cuenta_codigo);
            ArrayList asociacionesRecibe = asociacionModel.obtenerAsociacionesRecibe(cuenta_codigo);
            ArrayList unirListas = new ArrayList();
            
            unirListas.addAll(asociacionesEnvia);
            unirListas.addAll(asociacionesRecibe);
            
            ArrayList<Cuenta> cuentasAsociadas = new ArrayList<>();
            
            for (int i = 0; i < unirListas.size(); i++) {
                cuentasAsociadas.add(cuentaModel.obtenerCuenta(Long.parseLong(unirListas.get(i).toString())));
            }
            
            ArrayList<Cuenta> cuentasRestantes = cuentaModel.obtenerCuentasRestantes(cliente_codigo,cuenta_codigo);
            
            request.setAttribute("cuentasAsociadas", cuentasAsociadas);
            request.setAttribute("cuentasRestantes", cuentasRestantes);
            request.setAttribute("cuenta_seleccionada", cuenta_seleccionada);
            
            request.getRequestDispatcher("/Cliente/TablaCuentasParaTransaccion.jsp").forward(request, response);
            
            
            
            
        } catch (Exception e) {
        }
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }


}
