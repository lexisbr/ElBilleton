/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Modelos.Banco.CuentaModel;
import Modelos.Conexion.Conexion;
import Modelos.Historial.HistorialClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Historiales.HistorialCliente;
import Objetos.Usuarios.Cliente;
import Objetos.Usuarios.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class ClienteModel {

    //Constantes estaticas para base de datos
    private final String CLIENTES = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME;
    private final String BUSCAR_CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CLIENTE_ID_DB_NAME + " =?";
    private final String CREAR_CLIENTE_SIN_CODIGO = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME
            + "," + Cliente.FECHA_NACIMIENTO_DB_NAME + "," + Cliente.PDFDPI_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String CREAR_CLIENTE_CON_CODIGO = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.CLIENTE_ID_DB_NAME + "," + Cliente.NOMBRE_DB_NAME + ","
            + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + "," + Cliente.FECHA_NACIMIENTO_DB_NAME  + "," + Cliente.PDFDPI_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";

    private static Connection connection = Conexion.getInstance();

    HistorialClienteModel historial_cliente = new HistorialClienteModel();

    /**
     * Agregamos un nuevo cliente desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public void agregarClienteArchivo(Cliente cliente) throws SQLException {
        CuentaModel modeloCuenta = new CuentaModel();
        PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

        preSt.setLong(1, cliente.getCodigo());
        preSt.setString(2, cliente.getNombre());
        preSt.setString(3, cliente.getDpi());
        preSt.setString(4, cliente.getDireccion());
        preSt.setString(5, cliente.getSexo());
        preSt.setDate(6, cliente.getFecha_nacimiento());
        preSt.setBlob(7, cliente.getPdfDPI());
        preSt.setString(8, cliente.getPassword());

        preSt.executeUpdate();

        historial_cliente.agregarHistorialCliente(cliente);
    }

    /**
     * Agregamos un nuevo cliente, al completar la insercion devuelve el codigo
     * autogenerado.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public long agregarCliente(Cliente cliente) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

        preSt.setString(1, cliente.getNombre());
        preSt.setString(2, cliente.getDpi());
        preSt.setString(3, cliente.getDireccion());
        preSt.setString(4, cliente.getSexo());
        preSt.setDate(5, cliente.getFecha_nacimiento());
        preSt.setBlob(6, cliente.getPdfDPI());
        preSt.setString(7, cliente.getPassword());

        preSt.executeUpdate();
        
        historial_cliente.agregarHistorialCliente(cliente);
        
        ResultSet result = preSt.getGeneratedKeys();
        if (result.first()) {
            return result.getLong(1);
        }

        return -1;
    }
    
    
    

    /**
     * Metodo que busca un cliente por su codigo
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Cliente obtenerCliente(long codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTE);
        preSt.setLong(1, codigo);
        ResultSet result = preSt.executeQuery();

        Cliente cliente = null;

        while (result.next()) {
            cliente = new Cliente(
                    result.getLong(Cliente.CLIENTE_ID_DB_NAME),
                    result.getString(Cliente.NOMBRE_DB_NAME),
                    result.getString(Cliente.DPI_DB_NAME),
                    result.getString(Cliente.DIRECCION_DB_NAME),
                    result.getString(Cliente.SEXO_DB_NAME),
                    result.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                    result.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                    result.getString(Cliente.PASSWORD_DB_NAME)
            );
        }
        return cliente;
    }

    /**
     * Verifica que usuario exista y que la password sea correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Cliente validacionLogin(long codigo, String password) throws SQLException {
        Cliente cliente = obtenerCliente(codigo);
        if (cliente != null && cliente.getPassword().equals(password)) {
            return cliente;
        }
        return null;
    }
}
