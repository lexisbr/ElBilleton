/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.LimitesGerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class LimitesGerenteModel {

    private final String LIMITES = "SELECT * FROM " + LimitesGerente.LIMITES_GERENTE_DB_NAME;
    private final String MODIFICAR_LIMITES = "UPDATE " + LimitesGerente.LIMITES_GERENTE_DB_NAME + " SET " + LimitesGerente.LIMITE_REPORTE2_NAME + "=?,"
            + LimitesGerente.LIMITE_REPORTE3_NAME + " =?";

    private static Connection connection = Conexion.getInstance();

    public LimitesGerente obtenerLimites() {
        try {
            PreparedStatement preSt = connection.prepareStatement(LIMITES);
            ResultSet result = preSt.executeQuery();

            LimitesGerente limites = null;
            while (result.next()) {
                limites = new LimitesGerente(
                        result.getDouble(LimitesGerente.LIMITE_REPORTE2_NAME),
                        result.getDouble(LimitesGerente.LIMITE_REPORTE3_NAME)
                );
            }
            return limites;
        } catch (SQLException e) {
            System.out.println("Error al cargar limites "+e);
            return null;
        }
    }
    
    public void modificarLimites(LimitesGerente limites){
        try {
            PreparedStatement preSt = connection.prepareStatement(MODIFICAR_LIMITES);
            
            preSt.setDouble(1, limites.getLimite_reporte2());
            preSt.setDouble(2, limites.getLimite_reporte3());
            preSt.executeUpdate();
            
            
        } catch (SQLException e) {
            System.out.println("Error al actualizar limites "+e);
        }
        
    }
}
