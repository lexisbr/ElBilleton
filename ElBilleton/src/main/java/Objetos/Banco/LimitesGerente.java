/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos.Banco;

/**
 *
 * @author lex
 */
public class LimitesGerente {

    public static final String LIMITES_GERENTE_DB_NAME = "LIMITES_GERENTE";
    public static final String LIMITE_REPORTE2_NAME = "limite_reporte2";
    public static final String LIMITE_REPORTE3_NAME = "limite_reporte3";

    private double limite_reporte2;
    private double limite_reporte3;

    public LimitesGerente() {
    }

    public LimitesGerente(double limite_reporte2, double limite_reporte3) {
        this.limite_reporte2 = limite_reporte2;
        this.limite_reporte3 = limite_reporte3;
    }

    public double getLimite_reporte2() {
        return limite_reporte2;
    }

    public double getLimite_reporte3() {
        return limite_reporte3;
    }

    public void setLimite_reporte2(double limite_reporte2) {
        this.limite_reporte2 = limite_reporte2;
    }

    public void setLimite_reporte3(double limite_reporte3) {
        this.limite_reporte3 = limite_reporte3;
    }

}
