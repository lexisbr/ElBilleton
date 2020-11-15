/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Historial;

import Modelos.Conexion.Conexion;
import Objetos.Historiales.HistorialCliente;
import Objetos.Usuarios.Cliente;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class HistorialClienteModel {

    //Constantes para querys 
    private final String CREAR_HISTORIAL_CLIENTE = "INSERT INTO " + HistorialCliente.HISTORIAL_CLIENTE_DB_NAME + " (" + HistorialCliente.NOMBRE_DB_NAME + "," + HistorialCliente.DPI_DB_NAME + "," + HistorialCliente.DIRECCION_DB_NAME + "," + HistorialCliente.SEXO_DB_NAME
            + "," + HistorialCliente.FECHA_NACIMIENTO_DB_NAME + "," + HistorialCliente.PDFDPI_DB_NAME + "," + HistorialCliente.PASSWORD_DB_NAME + "," + HistorialCliente.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";
    private final String HISTORIA_CLIENTE = "SELECT * FROM " + HistorialCliente.HISTORIAL_CLIENTE_DB_NAME + " WHERE " + HistorialCliente.CLIENTE_CODIGO_DB_NAME + "=?";
    private final String DPI_CLIENTE = "SELECT " + HistorialCliente.PDFDPI_DB_NAME + " FROM " + HistorialCliente.HISTORIAL_CLIENTE_DB_NAME + " WHERE " + HistorialCliente.CODIGO_DB_NAME + "= ?";

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
        try {
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

        } catch (SQLException e) {
            System.out.println("Error al agregar historial de cliente " + e);
        }
    }

    /**
     * Devuelve el historial de cambios segun el codigo ingresado
     *
     * @param codigo
     * @return
     */
    public ArrayList<HistorialCliente> obtenerHistorialCliente(long codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(HISTORIA_CLIENTE);
            preSt.setLong(1, codigo);
            ArrayList<HistorialCliente> listaHistorial = new ArrayList<>();

            HistorialCliente historialCliente = null;

            ResultSet rs = preSt.executeQuery();
            while (rs.next()) {
                historialCliente = new HistorialCliente(
                        rs.getInt(HistorialCliente.CODIGO_DB_NAME),
                        rs.getString(HistorialCliente.NOMBRE_DB_NAME),
                        rs.getString(HistorialCliente.DPI_DB_NAME),
                        rs.getString(HistorialCliente.DIRECCION_DB_NAME),
                        rs.getString(HistorialCliente.SEXO_DB_NAME),
                        rs.getDate(HistorialCliente.FECHA_NACIMIENTO_DB_NAME),
                        rs.getBinaryStream(HistorialCliente.PDFDPI_DB_NAME),
                        rs.getString(HistorialCliente.PASSWORD_DB_NAME),
                        rs.getLong(HistorialCliente.CLIENTE_CODIGO_DB_NAME)
                );
                listaHistorial.add(historialCliente);
            }

            return listaHistorial;

        } catch (SQLException e) {
            System.out.println("Error al obtener historial de cliente " + e);
            return null;
        }
    }

    /**
     * Devuelve el archivo pdf del dpi
     *
     * @param codigo
     * @return
     */
    public InputStream obtenerDPI(long codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(DPI_CLIENTE);
            preSt.setLong(1, codigo);

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                return result.getBlob(1).getBinaryStream();
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener dpi de db " + e);
            return null;
        }
        return null;
    }

}
