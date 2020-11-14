/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Clases.Encriptador;
import Modelos.Conexion.Conexion;
import Modelos.Historial.HistorialGerenteModel;
import Objetos.Usuarios.Cliente;
import Objetos.Usuarios.Gerente;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;

/**
 *
 * @author lex
 */
public class GerenteModel {

    //Constantes estaticas para base de datos
    private final String GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_NAME;
    private final String BUSCAR_GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_NAME + " WHERE " + Gerente.GERENTE_ID_DB_NAME + " =?";
    private final String TURNO_GERENTE = "SELECT turno FROM " + Gerente.GERENTE_DB_NAME + " WHERE " + Gerente.GERENTE_ID_DB_NAME + " =?";
    private final String CREAR_GERENTE_SIN_CODIGO = "INSERT INTO " + Gerente.GERENTE_DB_NAME + " (" + Gerente.NOMBRE_DB_NAME + "," + Gerente.TURNO_DB_NAME + "," + Gerente.DPI_DB_NAME + "," + Gerente.DIRECCION_DB_NAME
            + "," + Gerente.SEXO_DB_NAME + "," + Gerente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_GERENTE_CON_CODIGO = "INSERT INTO " + Gerente.GERENTE_DB_NAME + " (" + Gerente.GERENTE_ID_DB_NAME + "," + Gerente.NOMBRE_DB_NAME + "," + Gerente.TURNO_DB_NAME
            + "," + Gerente.DPI_DB_NAME + "," + Gerente.DIRECCION_DB_NAME + "," + Gerente.SEXO_DB_NAME + "," + Gerente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String MODIFICAR_GERENTE = "UPDATE " + Gerente.GERENTE_DB_NAME + " SET " + Gerente.NOMBRE_DB_NAME + "=?,"
            + Gerente.TURNO_DB_NAME + "=?," + Gerente.DPI_DB_NAME + "=?," + Gerente.DIRECCION_DB_NAME + "=?," + Gerente.SEXO_DB_NAME + "=?,"
            + Gerente.PASSWORD_DB_NAME + "=? WHERE " + Gerente.GERENTE_ID_DB_NAME + " =?";
    
    private static Connection connection = Conexion.getInstance();

    HistorialGerenteModel historialGerente = new HistorialGerenteModel();

    /**
     * Agregamos un nuevo gerente desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param gerente
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public long agregarGerenteArchivo(Gerente gerente) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_GERENTE_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);
            //Se encripta la password
            gerente.setPassword(Encriptador.encriptar(gerente.getPassword()));

            preSt.setLong(1, gerente.getCodigo());
            preSt.setString(2, gerente.getNombre());
            preSt.setString(3, gerente.getTurno());
            preSt.setString(4, gerente.getDpi());
            preSt.setString(5, gerente.getDireccion());
            preSt.setString(6, gerente.getSexo());
            preSt.setString(7, gerente.getPassword());

            preSt.executeUpdate();

            historialGerente.agregarHistorialGerente(gerente);

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }

        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en gerente " + e);
        }
        return -1;
    }

    /**
     * Agregamos gerente desde crear, al finalizar la insercion devuelve el
     * codigo autogenerado
     *
     * @param gerente
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public long agregarGerente(Gerente gerente) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_GERENTE_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

            //Se encripta la password
            gerente.setPassword(Encriptador.encriptar(gerente.getPassword()));

            preSt.setString(1, gerente.getNombre());
            preSt.setString(2, gerente.getTurno());
            preSt.setString(3, gerente.getDpi());
            preSt.setString(4, gerente.getDireccion());
            preSt.setString(5, gerente.getSexo());
            preSt.setString(6, gerente.getPassword());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en gerente " + e);
        }
        return -1;
    }

    /**
     * Metodo que busca un gerente por su codigo
     *
     * @param codigo
     * @return Gerente
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public Gerente obtenerGerente(long codigo) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
            preSt.setLong(1, codigo);
            ResultSet result = preSt.executeQuery();

            Gerente gerente = null;

            while (result.next()) {
                gerente = new Gerente(
                        result.getLong(Gerente.GERENTE_ID_DB_NAME),
                        result.getString(Gerente.NOMBRE_DB_NAME),
                        result.getString(Gerente.TURNO_DB_NAME),
                        result.getString(Gerente.DPI_DB_NAME),
                        result.getString(Gerente.DIRECCION_DB_NAME),
                        result.getString(Gerente.SEXO_DB_NAME),
                        result.getString(Gerente.PASSWORD_DB_NAME)
                );
            }
            gerente.setPassword(Encriptador.desencriptar(gerente.getPassword()));
            return gerente;

        } catch (NullPointerException |UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en gerente " + e);
            return null;
        }
        
    }
    
     public void modificarGerente(Gerente gerente) throws SQLException{
        try {
            gerente.setPassword(Encriptador.encriptar(gerente.getPassword()));
            PreparedStatement preSt = connection.prepareStatement(MODIFICAR_GERENTE);

           preSt.setString(1, gerente.getNombre());
            preSt.setString(2, gerente.getTurno());
            preSt.setString(3, gerente.getDpi());
            preSt.setString(4, gerente.getDireccion());
            preSt.setString(5, gerente.getSexo());
            preSt.setString(6, gerente.getPassword());
            preSt.setLong(7, gerente.getCodigo());
            preSt.executeUpdate();

        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en actualizar "+e);
        }

    }

    /**
     * Verifica que usuario exista y que la password sea correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public Gerente validacionLogin(long codigo, String password) throws SQLException, UnsupportedEncodingException {
         Gerente gerente = obtenerGerente(codigo);
        try {
            if (gerente != null && gerente.getPassword().equals(password)) {
                return gerente;
            }
        } catch (Exception e) {
            System.out.println("Error en gerente " + e);
        }
        return null;
    }

    /**
     * Obtiene turno para verificar que este dentro de su turno para permitir
     * ejectuar acciones de creacion y edicion
     *
     * @param codigo
     * @return String
     * @throws SQLException
     */
    public String obtenerTurno(long codigo) throws SQLException {

        PreparedStatement preSt = connection.prepareStatement(TURNO_GERENTE);
        preSt.setLong(1, codigo);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            return result.getString(Gerente.TURNO_DB_NAME);
        }
        return null;
    }

    /**
     * Verifica si esta dentro de su turno
     *
     * @param codigo
     * @return Boolean
     * @throws SQLException
     */
    public Boolean estaDentroTurno(long codigo) throws SQLException {
        String turno = obtenerTurno(codigo);
        LocalTime horaActual = LocalTime.now();
        LocalTime horaInicio;
        LocalTime horaFinal;
        if (turno.equals("MATUTINO")) {
            horaInicio = LocalTime.parse("00:00");
            horaFinal = LocalTime.parse("23:59");
            return horaFinal.isAfter(horaActual) && horaInicio.isBefore(horaActual);

        } else if (turno.equals("VESPERTINO")) {
            horaInicio = LocalTime.parse("00:00");
            horaFinal = LocalTime.parse("23:59");
            return horaFinal.isAfter(horaActual) && horaInicio.isBefore(horaActual);

        }

        return false;
    }

}
