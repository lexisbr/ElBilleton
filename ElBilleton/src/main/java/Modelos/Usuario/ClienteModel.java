/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Modelos.Conexion.Conexion;
import Objetos.Usuarios.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class ClienteModel {
    
    //Constantes estaticas para base de datos
    private final String CLIENTES = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME;
    private final String BUSCAR_CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CLIENTE_ID_DB_NAME + " =?";

    private static Connection connection = Conexion.getInstance();
    
    /*
        Metodo que busca un cliente por su codigo
    */

    public Cliente obtenerCliente(int codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTE);
        preSt.setInt(1, codigo);
        ResultSet result = preSt.executeQuery();

        Cliente cliente = null;

        while (result.next()) {
            cliente = new Cliente(
                    result.getInt(Cliente.CLIENTE_ID_DB_NAME),
                    result.getString(Cliente.NOMBRE_DB_NAME),
                    result.getInt(Cliente.DPI_DB_NAME),
                    result.getString(Cliente.DIRECCION_DB_NAME),
                    result.getString(Cliente.SEXO_DB_NAME),
                    result.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                    result.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                    result.getString(Cliente.PASSWORD_DB_NAME)
            );
        }
        return cliente;
    }
    
    /*
        Verifica que usuario exista y que la password sea correctas
    */
    public Cliente validacionLogin(int codigo, String password) throws SQLException {
        Cliente cliente = obtenerCliente(codigo);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }
}
