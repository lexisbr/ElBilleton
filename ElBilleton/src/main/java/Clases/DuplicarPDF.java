/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @author lex
 */
public class DuplicarPDF {

    private InputStream pdfDPI;

    ByteArrayOutputStream baOS = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len;

    public DuplicarPDF(InputStream pdfDPI) {
        this.pdfDPI = pdfDPI;
    }

    public byte[] obtenerArrayDatos() {
        try {
            
            while ((len = getPdfDPI().read(buffer)) > -1) {
                baOS.write(buffer, 0, len);
            }
            baOS.flush();
            
            return baOS.toByteArray();
            
        } catch (IOException e) {
            System.out.println("Error al duplicar archivo");
            return null;
        }
    }

    public InputStream getPdfDPI() {
        return pdfDPI;
    }
    
    

}
