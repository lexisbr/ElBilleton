/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Paths;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

/**
 *
 * @author lex
 */
public class CrearArchivo {
    
    private String pathArchivos;
    
    public File construirArchivo(String parametro,HttpServletRequest request){
        try {
            Part filePart = request.getPart(parametro);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            InputStream inputStream = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(fileName);
            inputStream.transferTo(outputStream);
            File file = Paths.get(filePart.getSubmittedFileName()).toFile();
            
            setPathArchivos(file.getAbsolutePath().replace("/"+file.getName(), "/"));
            return file;
            
        } catch (IOException | ServletException e) {
            System.out.println("Construir Error: " + e.getMessage());
            return null;
        }
    }
    
    public InputStream obtenerArchivo(String parametro,HttpServletRequest request){
        try {
            Part filePart = request.getPart(parametro);
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
            return filePart.getInputStream();
        } catch (IOException | ServletException e) {
            System.out.println("Obtener archivo Error: " + e.getMessage());
            return null;
        }
    }
    
    public void crearArchivo(Part filePart){
        try {
            InputStream inputStream = filePart.getInputStream();
            OutputStream outputStream = new FileOutputStream(Paths.get(filePart.getSubmittedFileName()).getFileName().toString());
            inputStream.transferTo(outputStream);
            
            File file = Paths.get(filePart.getSubmittedFileName()).toFile();
            setPathArchivos(file.getAbsolutePath().replace("/"+file.getName(), "/"));
        } catch (IOException e) {
            System.out.println("Crear archivo Error: " + e.getMessage());
        }
    }

    public void setPathArchivos(String pathArchivos) {
        this.pathArchivos = pathArchivos;
    }

    public String getPathArchivos() {
        return pathArchivos;
    }
    
    
    
}
