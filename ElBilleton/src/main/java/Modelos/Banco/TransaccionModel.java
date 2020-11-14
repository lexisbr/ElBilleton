/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Transaccion;
import Objetos.Banco.Transaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author lex
 */
public class TransaccionModel {

    private final String CREAR_TRANSACCION_SIN_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_TRANSACCION_CON_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.CODIGO_DB_NAME + "," + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";

    private static Connection connection = Conexion.getInstance();

    /**
     * Agregamos una nueva cuenta desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public void agregarTransaccionArchivo(Transaccion transaccion) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_TRANSACCION_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

            preSt.setLong(1, transaccion.getCodigo());
            preSt.setDate(2, transaccion.getFecha());
            preSt.setTime(3, transaccion.getHora());
            preSt.setString(4, transaccion.getTipo());
            preSt.setDouble(5, transaccion.getMonto());
            preSt.setLong(6, transaccion.getCuenta_codigo());
            preSt.setLong(7, transaccion.getCajero_codigo());

            preSt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en gerente " + e);
        }

    }

    /**
     * Agregamos una nueva cuenta, al completar la insercion devuelve el codigo
     * autogenerado.
     *
     * @param transaccion
     * @return
     * @throws SQLException
     */
    public long agregarTransaccion(Transaccion transaccion) throws SQLException {
        try {

            PreparedStatement preSt = connection.prepareStatement(CREAR_TRANSACCION_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, transaccion.getFecha());
            preSt.setTime(2, transaccion.getHora());
            preSt.setString(3, transaccion.getTipo());
            preSt.setDouble(4, transaccion.getMonto());
            preSt.setLong(5, transaccion.getCuenta_codigo());
            preSt.setLong(6, transaccion.getCajero_codigo());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Error en gerente " + e);
        }
        return -1;
    }

}
