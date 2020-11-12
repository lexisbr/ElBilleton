/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Usuarios;

import java.io.InputStream;
import java.util.Date;

/**
 *
 * @author lex
 */
public class Cliente {
    
    //Constantes estaticas para base de datos
    public static final String CLIENTE_DB_NAME = "CLIENTE";
    public static final String CLIENTE_ID_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String DPI_DB_NAME = "dpi";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String FECHA_NACIMIENTO_DB_NAME = "fecha_nacimiento";
    public static final String PDFDPI_DB_NAME = "pdfDPI";
    public static final String PASSWORD_DB_NAME = "password";
    
    private int codigo;
    private String nombre;
    private int dpi;
    private String direccion;
    private String sexo;
    private Date fecha_nacimiento;
    private InputStream pdfDPI;
    private String password;

    public Cliente() {
    }

    public Cliente(int codigo, String nombre, int dpi, String direccion, String sexo, Date fecha_nacimiento, InputStream pdfDPI, String password) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.dpi = dpi;
        this.direccion = direccion;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.pdfDPI = pdfDPI;
        this.password = password;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDpi() {
        return dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public InputStream getPdfDPI() {
        return pdfDPI;
    }

    public String getPassword() {
        return password;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDpi(int dpi) {
        this.dpi = dpi;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public void setPdfDPI(InputStream pdfDPI) {
        this.pdfDPI = pdfDPI;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    
    
}
