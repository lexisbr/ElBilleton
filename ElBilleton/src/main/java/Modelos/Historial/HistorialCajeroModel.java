/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Historial;

import Modelos.Conexion.Conexion;
import Objetos.Historiales.HistorialCajero;
import Objetos.Usuarios.Cajero;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class HistorialCajeroModel {
        //Constantes para base de datos
    private final String CREAR_HISTORIAL_CAJERO = "INSERT INTO " + HistorialCajero.HISTORIAL_CAJERO_DB_NAME + " (" + HistorialCajero.NOMBRE_DB_NAME + "," + HistorialCajero.TURNO_DB_NAME + "," + 
            HistorialCajero.DPI_DB_NAME + "," + HistorialCajero.DIRECCION_DB_NAME + "," + HistorialCajero.SEXO_DB_NAME + "," + HistorialCajero.PASSWORD_DB_NAME + "," + HistorialCajero.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String HISTORIAL_CAJERO = "SELECT * FROM "+HistorialCajero.HISTORIAL_CAJERO_DB_NAME+" WHERE "+HistorialCajero.CAJERO_CODIGO_DB_NAME+" =? ORDER BY "+HistorialCajero.CODIGO_DB_NAME+" DESC";;
    
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
    
    
    /**
     * Obtenemos el historial de cambios de un cajero en especifico
     * @param codigo
     * @return 
     */
    public ArrayList<HistorialCajero> obtenerHistorial(long codigo){
        try {
            PreparedStatement preSt = connection.prepareStatement(HISTORIAL_CAJERO);
            preSt.setLong(1, codigo);
            
            ArrayList <HistorialCajero> listaHistorial = new ArrayList<>();
            HistorialCajero historialCajero = null;
            
            ResultSet rs = preSt.executeQuery();
            while (rs.next()) {
                historialCajero = new HistorialCajero(
                rs.getInt(HistorialCajero.CODIGO_DB_NAME),
                rs.getString(HistorialCajero.NOMBRE_DB_NAME),
                rs.getString(HistorialCajero.TURNO_DB_NAME),
                rs.getString(HistorialCajero.DPI_DB_NAME),
                rs.getString(HistorialCajero.DIRECCION_DB_NAME),
                rs.getString(HistorialCajero.SEXO_DB_NAME),
                rs.getString(HistorialCajero.PASSWORD_DB_NAME),
                rs.getLong(HistorialCajero.CAJERO_CODIGO_DB_NAME)
                );
                listaHistorial.add(historialCajero);
            }
            
           return listaHistorial;
        } catch (SQLException e) {
            System.out.println("Error al obtener el historial de cajero "+e);
            return null;
        }
    }
    
}
