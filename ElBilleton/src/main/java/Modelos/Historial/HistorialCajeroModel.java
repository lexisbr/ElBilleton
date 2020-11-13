/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Historial;

import Modelos.Conexion.Conexion;
import Objetos.Historiales.HistorialCajero;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class HistorialCajeroModel {
        //Constantes para base de datos
    private final String CREAR_HISTORIAL_CAJERO = "INSERT INTO " + HistorialCajero.HISTORIAL_CAJERO_DB_NAME + " (" + HistorialCajero.NOMBRE_DB_NAME + "," + HistorialCajero.TURNO_DB_NAME + "," + 
            HistorialCajero.DPI_DB_NAME + "," + HistorialCajero.DIRECCION_DB_NAME + "," + HistorialCajero.SEXO_DB_NAME + "," + HistorialCajero.PASSWORD_DB_NAME + "," + HistorialCajero.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    private static Connection connection = Conexion.getInstance();
    
    /**
     * Agregamos un nuevo registro al historial del cajero cuando se realiza un
     * cambio en este.
     *
     * @param HIstorialGerente
     * @return
     * @throws SQLException
     */
    public void agregarHistorialCajero(Cajero historial_cajero) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_HISTORIAL_CAJERO);
        System.out.println("Codigo: "+historial_cajero.getCodigo());
        preSt.setString(1, historial_cajero.getNombre());
        preSt.setString(2, historial_cajero.getTurno());
        preSt.setString(3, historial_cajero.getDpi());
        preSt.setString(4, historial_cajero.getDireccion());
        preSt.setString(5, historial_cajero.getSexo());
        preSt.setString(6, historial_cajero.getPassword());
        preSt.setLong(7, historial_cajero.getCodigo());

        preSt.executeUpdate();
    }
    
}
