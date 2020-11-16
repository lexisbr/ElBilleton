/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Banco;

import java.sql.Date;

/**
 *
 * @author lex
 */
public class Solicitud {
    //Constantes para querys
    public static final String SOLICITUD_DB_NAME = "SOLICITUD";
    public static final String CODIGO_DB_NAME = "codigo";
    public static final String FECHA_DB_NAME = "fecha";
    public static final String ESTADO_DB_NAME = "estado";
    public static final String CUENTA_ENVIA_CODIGO_DB_NAME = "cuenta_codigo_envia";
    public static final String CUENTA_RECIBE_CODIGO_DB_NAME = "cuenta_codigo_recibe";
    
    private int codigo;
    private Date fecha;
    private String estado;
    private Long cuenta_codigo_envia;
    private Long cuenta_codigo_recibe;

    public Solicitud() {
    }

    public Solicitud(int codigo, Date fecha, String estado, Long cuenta_codigo_envia, Long cuenta_codigo_recibe) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.estado = estado;
        this.cuenta_codigo_envia = cuenta_codigo_envia;
        this.cuenta_codigo_recibe = cuenta_codigo_recibe;
    }

    public int getCodigo() {
        return codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getEstado() {
        return estado;
    }

    public Long getCuenta_codigo_envia() {
        return cuenta_codigo_envia;
    }

    public Long getCuenta_codigo_recibe() {
        return cuenta_codigo_recibe;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setCuenta_codigo_envia(Long cuenta_codigo_envia) {
        this.cuenta_codigo_envia = cuenta_codigo_envia;
    }

    public void setCuenta_codigo_recibe(Long cuenta_codigo_recibe) {
        this.cuenta_codigo_recibe = cuenta_codigo_recibe;
    }
    
}
