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

/**
 *
 * @author lex
 */
public class GerenteModel {
    
    //Constantes estaticas para base de datos
    private final String GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_NAME;
    private final String BUSCAR_GERENTE = "SELECT * FROM " + Gerente.GERENTE_DB_NAME + " WHERE " + Gerente.GERENTE_ID_DB_NAME + " =?";

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

}
