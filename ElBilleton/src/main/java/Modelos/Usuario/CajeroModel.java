/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Clases.Encriptador;
import Modelos.Conexion.Conexion;
import Modelos.Historial.HistorialCajeroModel;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Gerente;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lex
 */
public class CajeroModel {

    //Constantes estaticas para base de datos
    private final String CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME;
    private final String BUSCAR_CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME + " WHERE " + Cajero.CAJERO_ID_DB_NAME + " =?";
    private final String CREAR_CAJERO_SIN_CODIGO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME
            + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_CAJERO_CON_CODIGO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.CAJERO_ID_DB_NAME + "," + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME
            + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    private static Connection connection = Conexion.getInstance();

    HistorialCajeroModel historialCajero = new HistorialCajeroModel();

    /**
     * Agregamos un nuevo cajero desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param cajero
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public void agregarCajeroArchivo(Cajero cajero) throws SQLException, UnsupportedEncodingException {

        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CAJERO_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

            //Se encripta la password
            cajero.setPassword(Encriptador.encriptar(cajero.getPassword()));

            preSt.setLong(1, cajero.getCodigo());
            preSt.setString(2, cajero.getNombre());
            preSt.setString(3, cajero.getTurno());
            preSt.setString(4, cajero.getDpi());
            preSt.setString(5, cajero.getDireccion());
            preSt.setString(6, cajero.getSexo());
            preSt.setString(7, cajero.getPassword());

            preSt.executeUpdate();

            historialCajero.agregarHistorialCajero(cajero);

        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cajero " + e);
        }

    }

    /**
     * Agregamos cajero desde crear, al finalizar la insercion devuelve el
     * codigo autogenerado
     *
     * @param cajero
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public long agregarCajero(Cajero cajero) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CAJERO_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);
            //Se encripta password
            cajero.setPassword(Encriptador.encriptar(cajero.getPassword()));

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getTurno());
            preSt.setString(3, cajero.getDpi());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            preSt.setString(6, cajero.getPassword());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }

        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cajero " + e);
        }
        return -1;
    }

    /**
     * Obtenemos el cajeron con el codigo dado
     *
     * @param codigo
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public Cajero obtenerCajero(long codigo) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJERO);
            preSt.setLong(1, codigo);
            ResultSet result = preSt.executeQuery();

            Cajero cajero = null;

            while (result.next()) {
                cajero = new Cajero(
                        result.getLong(Cajero.CAJERO_ID_DB_NAME),
                        result.getString(Cajero.NOMBRE_DB_NAME),
                        result.getString(Cajero.TURNO_DB_NAME),
                        result.getString(Cajero.DPI_DB_NAME),
                        result.getString(Cajero.DIRECCION_DB_NAME),
                        result.getString(Cajero.SEXO_DB_NAME),
                        result.getString(Cajero.PASSWORD_DB_NAME)
                );
            }
            cajero.setPassword(Encriptador.desencriptar(cajero.getPassword()));
            return cajero;

        } catch (NullPointerException | UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cajero " + e);
        }
        return null;
    }

    /**
     * Se valida que el usuario exista y que sus credenciales sean correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public Cajero validacionLogin(long codigo, String password) throws SQLException, UnsupportedEncodingException {
        try {
            Cajero cajero = obtenerCajero(codigo);
            if (cajero != null && cajero.getPassword().equals(password)) {
                return cajero;
            }
            

        } catch (UnsupportedEncodingException | SQLException e) {
             System.out.println("Error en cajero " + e);
        }
        return null;
    }

}
