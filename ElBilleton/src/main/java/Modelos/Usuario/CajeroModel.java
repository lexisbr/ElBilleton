/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Clases.Encriptador;
import Modelos.Conexion.Conexion;
import Modelos.Historial.HistorialCajeroModel;
import Objetos.Banco.Transaccion;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Cliente;
import Objetos.Usuarios.Gerente;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class CajeroModel {

    //Constantes estaticas para base de datos
    private final String CAJEROS_FILTRO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME + " WHERE " + Cliente.CLIENTE_ID_DB_NAME + " LIKE CONCAT('%',?,'%') && " + Cajero.CAJERO_ID_DB_NAME + "!= 101";
    private final String CAJEROS = "SELECT * FROM " + Cajero.CAJERO_DB_NAME + " WHERE " + Cajero.CAJERO_ID_DB_NAME + "!=101";
    private final String BUSCAR_CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME + " WHERE " + Cajero.CAJERO_ID_DB_NAME + " =?";
    private final String CREAR_CAJERO_SIN_CODIGO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME
            + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_CAJERO_CON_CODIGO = "INSERT INTO " + Cajero.CAJERO_DB_NAME + " (" + Cajero.CAJERO_ID_DB_NAME + "," + Cajero.NOMBRE_DB_NAME + "," + Cajero.TURNO_DB_NAME
            + "," + Cajero.DPI_DB_NAME + "," + Cajero.DIRECCION_DB_NAME + "," + Cajero.SEXO_DB_NAME + "," + Cajero.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String MODIFICAR_CAJERO = "UPDATE " + Cajero.CAJERO_DB_NAME + " SET " + Cajero.NOMBRE_DB_NAME + "=?,"
            + Cajero.TURNO_DB_NAME + "=?," + Cajero.DPI_DB_NAME + "=?," + Cajero.DIRECCION_DB_NAME + "=?," + Cajero.SEXO_DB_NAME + "=?,"
            + Cajero.PASSWORD_DB_NAME + "=? WHERE codigo=?";
    private final String CAJERO_MAS_TRANSACCIONES = "SELECT COUNT(*) AS transacciones,C.* FROM " + Cajero.CAJERO_DB_NAME + " C INNER JOIN " + Transaccion.TRANSACCION_DB_NAME
            + " T ON C.codigo=T.cajero_codigo WHERE C.codigo!=101 && T.fecha BETWEEN ? AND ? GROUP BY C.codigo ORDER BY transacciones DESC LIMIT 1";
    private final String TURNO_CAJERO = "SELECT turno FROM " + Cajero.CAJERO_DB_NAME + " WHERE " + Cajero.CAJERO_ID_DB_NAME + " =?";

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
     * Obtenemos el cajero que mas transacciones ha realizado en un intervalo de
     * tiempo
     *
     * @param codigo
     * @return
     * @throws SQLException
     * @throws java.io.UnsupportedEncodingException
     */
    public Cajero conMasTransacciones(Date fecha1, Date fecha2) throws SQLException, UnsupportedEncodingException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CAJERO_MAS_TRANSACCIONES);
            preSt.setDate(1, fecha1);
            preSt.setDate(2, fecha2);
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
            return cajero;

        } catch (NullPointerException | SQLException e) {
            System.out.println("Error en cajero " + e);
        }
        return null;
    }

    /**
     * Obtenemos una lista con los cajeros exitentes
     *
     * @return
     */
    public ArrayList<Cajero> obtenerCajeros() {
        try {
            ArrayList<Cajero> listaCajeros = new ArrayList<>();

            PreparedStatement preSt = connection.prepareStatement(CAJEROS);
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
                listaCajeros.add(cajero);
            }
            return listaCajeros;

        } catch (SQLException e) {
            System.out.println("Error en lista de cajeros " + e);
        }
        return null;
    }

    /**
     * Obtener cajeros basado en filtro por codigo
     *
     * @param codigo
     * @return
     */
    public ArrayList<Cajero> obtenerCajerosFiltrando(long codigo) {
        try {
            ArrayList<Cajero> listaCajeros = new ArrayList<>();

            PreparedStatement preSt = connection.prepareStatement(CAJEROS_FILTRO);
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
                listaCajeros.add(cajero);
            }
            return listaCajeros;

        } catch (SQLException e) {
            System.out.println("Error en lista de cajeros " + e);
        }
        return null;
    }

    /**
     * Se utiliza para actualizar los datos del cajero
     *
     * @param cajero
     * @throws SQLException
     */
    public void modificarCajero(Cajero cajero) throws SQLException {
        try {
            cajero.setPassword(Encriptador.encriptar(cajero.getPassword()));
            PreparedStatement preSt = connection.prepareStatement(MODIFICAR_CAJERO);

            preSt.setString(1, cajero.getNombre());
            preSt.setString(2, cajero.getTurno());
            preSt.setString(3, cajero.getDpi());
            preSt.setString(4, cajero.getDireccion());
            preSt.setString(5, cajero.getSexo());
            preSt.setString(6, cajero.getPassword());
            preSt.setLong(7, cajero.getCodigo());
            preSt.executeUpdate();

        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en actualizar " + e);
        }

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

    /**
     * Obtiene turno para verificar que este dentro de su turno para permitir
     * ejectuar acciones de creacion y edicion
     *
     * @param codigo
     * @return String
     * @throws SQLException
     */
    public String obtenerTurno(long codigo) throws SQLException {

        PreparedStatement preSt = connection.prepareStatement(TURNO_CAJERO);
        preSt.setLong(1, codigo);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            return result.getString(Cajero.TURNO_DB_NAME);
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
        if (turno.equalsIgnoreCase("MATUTINO")) {
            horaInicio = LocalTime.parse("06:00");
            horaFinal = LocalTime.parse("14:30");
            return horaFinal.isAfter(horaActual) && horaInicio.isBefore(horaActual);

        } else if (turno.equalsIgnoreCase("VESPERTINO")) {
            horaInicio = LocalTime.parse("13:00");
            horaFinal = LocalTime.parse("22:00");
            return horaFinal.isAfter(horaActual) && horaInicio.isBefore(horaActual);

        }
        return false;
    }

}
