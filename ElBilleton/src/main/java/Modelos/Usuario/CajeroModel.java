/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Modelos.Conexion.Conexion;
import Objetos.Usuarios.Cajero;
import Objetos.Usuarios.Gerente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class CajeroModel {

    //Constantes estaticas para base de datos
    private final String CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME;
    private final String BUSCAR_CAJERO = "SELECT * FROM " + Cajero.CAJERO_DB_NAME + " WHERE " + Cajero.CAJERO_ID_DB_NAME + " =?";

    private static Connection connection = Conexion.getInstance();

    /*
        Metodo que busca un cajero por su codigo
     */
    public Cajero obtenerCajero(int codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CAJERO);
        preSt.setInt(1, codigo);
        ResultSet result = preSt.executeQuery();

        Cajero cajero = null;

        while (result.next()) {
            cajero = new Cajero(
                    result.getInt(Cajero.CAJERO_ID_DB_NAME),
                    result.getString(Cajero.NOMBRE_DB_NAME),
                    result.getString(Cajero.TURNO_DB_NAME),
                    result.getInt(Cajero.DPI_DB_NAME),
                    result.getString(Cajero.DIRECCION_DB_NAME),
                    result.getString(Cajero.SEXO_DB_NAME),
                    result.getString(Cajero.PASSWORD_DB_NAME)
            );
        }
        return cajero;
    }

    /*
        Verifica que usuario exista y que la password sea correctas
     */
    public Cajero validacionLogin(int codigo, String password) throws SQLException {
        Cajero cajero = obtenerCajero(codigo);
        if (cajero != null && cajero.getPassword().equals(password)) {
            return cajero;
        }
        return null;
    }

}
