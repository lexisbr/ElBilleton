/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Historial;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Transaccion;
import Objetos.Historiales.HistorialGerente;
import Objetos.Usuarios.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lex
 */
public class HistorialGerenteModel {

    //Constantes para base de datos
    private final String CREAR_HISTORIAL_GERENTE = "INSERT INTO " + HistorialGerente.HISTORIAL_GERENTE_DB_NAME + " (" + HistorialGerente.NOMBRE_DB_NAME + "," + HistorialGerente.TURNO_DB_NAME + "," + 
            HistorialGerente.DPI_DB_NAME + "," + HistorialGerente.DIRECCION_DB_NAME + "," + HistorialGerente.SEXO_DB_NAME + "," + HistorialGerente.PASSWORD_DB_NAME + "," + HistorialGerente.GERENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    private static Connection connection = Conexion.getInstance();
    
    /**
     * Agregamos un nuevo registro al historial del gerente cuando se realiza un
     * cambio en este.
     *
     * @param HIstorialGerente
     * @return
     * @throws SQLException
     */
    public void agregarHistorialGerente(Gerente historial_gerente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_HISTORIAL_GERENTE);

        preSt.setString(1, historial_gerente.getNombre());
        preSt.setString(2, historial_gerente.getTurno());
        preSt.setString(3, historial_gerente.getDpi());
        preSt.setString(4, historial_gerente.getDireccion());
        preSt.setString(5, historial_gerente.getSexo());
        preSt.setString(6, historial_gerente.getPassword());
        preSt.setLong(7, historial_gerente.getCodigo());

        preSt.executeUpdate();
    }
}
