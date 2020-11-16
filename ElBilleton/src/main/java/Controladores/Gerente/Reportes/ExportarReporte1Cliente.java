/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente.Reportes;

import Modelos.Conexion.Conexion;
import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 *
 * @author lex
 */
@WebServlet(name = "ExportarReporte1Cliente", urlPatterns = {"/ExportarReporte1Cliente"})
public class ExportarReporte1Cliente extends HttpServlet {

    private static Connection connection = Conexion.getInstance();

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

            Map parametro = new HashMap();
            parametro.put("codigo", Long.parseLong(request.getParameter("codigo")));
            String path = request.getServletContext().getRealPath("/Recursos/Gerente/reporteHistorialCliente.jasper");
            JasperReport report = null;
            
            report = (JasperReport) JRLoader.loadObjectFromFile(path);

            JasperPrint jasperPrint = JasperFillManager.fillReport(report, parametro, connection);
            byte[] pdfBytes = JasperExportManager.exportReportToPdf(jasperPrint);

            response.flushBuffer();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "inline;filename=" + "archivo1" + ".pdf");
            response.getOutputStream().write(pdfBytes);
            response.flushBuffer();

        } catch (IOException | JRException e) {
            System.out.println("Error al procesar al crear reporte "+e);
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
