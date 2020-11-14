/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores.Gerente;

import Clases.CrearArchivo;
import Clases.LeerArchivo;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author lex
 */
@WebServlet("/ImportarDatos")
@MultipartConfig
public class ImportarDatos extends HttpServlet{
    
    CrearArchivo crearArchivo = new CrearArchivo();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String archivoXML="";
            
            ArrayList<Part> parts = new ArrayList<>();
            ArrayList<Part> partsMessy = new ArrayList<>(request.getParts());
            for (int i = 0; i < partsMessy.size(); i++) {
                if (partsMessy.get(i).getSubmittedFileName() != null) {
                    if (partsMessy.get(i).getSubmittedFileName().endsWith(".xml")) {
                        archivoXML = partsMessy.get(i).getSubmittedFileName();
                    }
                    parts.add(partsMessy.get(i));
                }
            }
            
            for (int i = 0; i < parts.size(); i++) {
                crearArchivo.crearArchivo(parts.get(i));
            }
            
            File file = new File(crearArchivo.getPathArchivos()+archivoXML);
            
            LeerArchivo analizador = new LeerArchivo();
            analizador.dividirEtiquetas(file, crearArchivo.getPathArchivos());
            response.sendRedirect(request.getContextPath() + "/Gerente/ExitoCargaDatos.jsp");
            
        } catch (IOException | ServletException e) {
            System.out.println("Error: "+e);
            response.sendRedirect(request.getContextPath() + "/Gerente/EstadoInactivo.jsp");
        }
        
    
    }
    
}
