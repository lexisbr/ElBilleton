/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Historial;

import Modelos.Conexion.Conexion;
import Objetos.Historiales.HistorialCliente;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class HistorialClienteModel {
    
    //Constantes para querys 
    private final String CREAR_HISTORIAL_CLIENTE = "INSERT INTO " + HistorialCliente.HISTORIAL_CLIENTE_DB_NAME + " (" + HistorialCliente.NOMBRE_DB_NAME + "," + HistorialCliente.DPI_DB_NAME + "," + HistorialCliente.DIRECCION_DB_NAME + "," + HistorialCliente.SEXO_DB_NAME
            + "," + HistorialCliente.FECHA_NACIMIENTO_DB_NAME +","+HistorialCliente.PDFDPI_DB_NAME+ "," + HistorialCliente.PASSWORD_DB_NAME +"," + HistorialCliente.CLIENTE_CODIGO_DB_NAME +") VALUES (?,?,?,?,?,?,?,?)";
    
    private static Connection connection = Conexion.getInstance();
    
    /**
     * Agregamos un nuevo registro al historial del cliente cuando se realiza un
     * cambio en este.
     *
     * @param HIstorialGerente
     * @return
     * @throws SQLException
     */
    public void agregarHistorialCliente(Cliente historial_cliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_HISTORIAL_CLIENTE);

        preSt.setString(1, historial_cliente.getNombre());
        preSt.setString(2, historial_cliente.getDpi());
        preSt.setString(3, historial_cliente.getDireccion());
        preSt.setString(4, historial_cliente.getSexo());
        preSt.setDate(5, historial_cliente.getFecha_nacimiento());
        preSt.setBlob(6, historial_cliente.getPdfDPI());
        preSt.setString(7, historial_cliente.getPassword());
        preSt.setLong(8, historial_cliente.getCodigo());

        preSt.executeUpdate();
    }
}
