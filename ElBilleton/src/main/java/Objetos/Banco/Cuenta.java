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
public class Cuenta {

    //Constantes estaticas para base de datos
    public static final String CUENTA_DB_NAME = "CUENTA";
    public static final String CUENTA_ID_DB_NAME = "codigo";
    public static final String FECHA_CREACION_DB_NAME = "fecha_creacion";
    public static final String MONTO_DB_NAME = "monto";
    public static final String CLIENTE_CODIGO_DB_NAME = "cliente_codigo";

    private long codigo;
    private Date fecha_creacion;
    private double monto;
    private long cliente_codigo;

    public Cuenta() {
    }

    public Cuenta(long codigo, Date fecha_creacion, double monto, long cliente_codigo) {
        this.codigo = codigo;
        this.fecha_creacion = fecha_creacion;
        this.monto = monto;
        this.cliente_codigo = cliente_codigo;
    }

    public long getCodigo() {
        return codigo;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public double getMonto() {
        return monto;
    }

    public long getCliente_codigo() {
        return cliente_codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setCliente_codigo(long cliente_codigo) {
        this.cliente_codigo = cliente_codigo;
    }
    
    
    

}
