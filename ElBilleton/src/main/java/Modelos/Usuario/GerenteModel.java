/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Modelos.Conexion.Conexion;
import Objetos.Usuarios.Cliente;
import Objetos.Usuarios.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    private static Connection connection = Conexion.getInstance();

    /*
        Metodo que busca un gerente por su codigo
     */
    public Gerente obtenerGerente(int codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_GERENTE);
        preSt.setInt(1, codigo);
        ResultSet result = preSt.executeQuery();

        Gerente gerente = null;

        while (result.next()) {
            gerente = new Gerente(
                    result.getInt(Gerente.GERENTE_ID_DB_NAME),
                    result.getString(Gerente.NOMBRE_DB_NAME),
                    result.getString(Gerente.TURNO_DB_NAME),
                    result.getInt(Gerente.DPI_DB_NAME),
                    result.getString(Gerente.DIRECCION_DB_NAME),
                    result.getString(Gerente.SEXO_DB_NAME),
                    result.getString(Gerente.PASSWORD_DB_NAME)
            );
        }
        return gerente;
    }

    /*
        Verifica que usuario exista y que la password sea correctas
     */
    public Gerente validacionLogin(int codigo, String password) throws SQLException {
        Gerente gerente = obtenerGerente(codigo);
        if (gerente != null && gerente.getPassword().equals(password)) {
            return gerente;
        }
        return null;
    }

    /*
        Obtiene turno para verificar que este dentro de su turno para permitir ejectuar acciones de creacion y edicion
     */
    public String obtenerTurno(int codigo) throws SQLException {

        PreparedStatement preSt = connection.prepareStatement(TURNO_GERENTE);
        preSt.setInt(1, codigo);
        ResultSet result = preSt.executeQuery();

        while (result.next()) {
            return result.getString(Gerente.TURNO_DB_NAME);
        }
        return null;
    }

    /*
        Verifica si esta dentro de su turno 
     */
    public Boolean estaDentroTurno(int codigo) throws SQLException {
        String turno = obtenerTurno(codigo);
        LocalTime horaActual = LocalTime.now();
        LocalTime horaInicio;
        LocalTime horaFinal;
        if (turno.equals("MATUTINO")) {
            horaInicio = LocalTime.parse("06:00");
            horaFinal = LocalTime.parse("14:30");
            if (horaFinal.isAfter(horaActual) && horaInicio.isBefore(horaActual)) {
                return true;
            } else {
                return false;
            }

        } else if (turno.equals("VESPERTINO")) {
            horaInicio = LocalTime.parse("13:00");
            horaFinal = LocalTime.parse("22:00");
            if (horaFinal.isAfter(horaActual) && horaInicio.isBefore(horaActual)) {
                return true;
            } else {
                return false;
            }

        }

        return false;
    }

}
