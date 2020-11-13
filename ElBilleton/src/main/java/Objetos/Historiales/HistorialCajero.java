/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Historiales;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author lex
 */
public class HistorialCajero {
    
    //Constantes estaticas para base de datos
    public static final String HISTORIAL_CAJERO_DB_NAME = "HISTORIAL_CAJERO";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String NOMBRE_DB_NAME = "nombre";
    public static final String TURNO_DB_NAME = "turno";
    public static final String DPI_DB_NAME = "dpi";
    public static final String DIRECCION_DB_NAME = "direccion";
    public static final String SEXO_DB_NAME = "sexo";
    public static final String PASSWORD_DB_NAME = "password";
    public static final String CAJERO_CODIGO_DB_NAME = "cajero_codigo";
    
    private int codigo;
    private String nombre;
    private String turno;
    private String dpi;
    private String direccion;
    private String sexo;
    private String password;
    private long cajero_codigo;

    public HistorialCajero() {
    }

    public HistorialCajero(int codigo, String nombre, String turno, String dpi, String direccion, String sexo, String password, long cajero_codigo) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.turno = turno;
        this.dpi = dpi;
        this.direccion = direccion;
        this.sexo = sexo;
        this.password = password;
        this.cajero_codigo = cajero_codigo;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTurno() {
        return turno;
    }

    public String getDpi() {
        return dpi;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getSexo() {
        return sexo;
    }

    public String getPassword() {
        return password;
    }

    public long getCajero_codigo() {
        return cajero_codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCajero_codigo(long cajero_codigo) {
        this.cajero_codigo = cajero_codigo;
    }
    
    
}
