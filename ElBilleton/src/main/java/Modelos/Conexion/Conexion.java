/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class Conexion {
    
    private static Connection connection = null;
    private static Conexion conexionDB;

    private Conexion() {
        String url = "jdbc:mysql://localhost/ElBilleton?allowPublicKeyRetrieval=true&useSSL=false";
        String user = "root";
        String password = "5200fcb1LEXIS.";

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            connection = DriverManager.getConnection(url, user, password);
        } catch (IllegalAccessException | InstantiationException | ClassNotFoundException ex) {
            System.out.println("Error: " + ex.getMessage());
            ex.printStackTrace();
        } catch (SQLException ex) {
            System.out.println("Error DB: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static Connection getInstance() {
        if (conexionDB == null) {
            conexionDB = new Conexion();
        }
        return connection;
    }
    
}
