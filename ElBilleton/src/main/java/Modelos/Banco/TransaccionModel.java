/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import Objetos.Banco.Transaccion;
import Objetos.Usuarios.Cliente;
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
public class TransaccionModel {

    private final String CREAR_TRANSACCION_SIN_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?)";
    private final String CREAR_TRANSACCION_CON_CODIGO = "INSERT INTO " + Transaccion.TRANSACCION_DB_NAME + " (" + Transaccion.CODIGO_DB_NAME + "," + Transaccion.FECHA_DB_NAME + "," + Transaccion.HORA_DB_NAME + "," + Transaccion.TIPO_DB_NAME + "," + Transaccion.MONTO_DB_NAME + "," + Transaccion.CUENTA_CODIGO_DB_NAME + "," + Transaccion.CAJERO_CODIGO_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String OBTENER_TRANSACCIONES_REPORTE2 = "SELECT T.*,CL." + Cliente.CLIENTE_ID_DB_NAME + " AS codigo_cliente FROM " + Transaccion.TRANSACCION_DB_NAME + " T INNER JOIN " + Cuenta.CUENTA_DB_NAME + " C "
            + "ON T." + Transaccion.CUENTA_CODIGO_DB_NAME + " = C." + Cuenta.CUENTA_ID_DB_NAME + " INNER JOIN " + Cliente.CLIENTE_DB_NAME + " CL ON CL." + Cliente.CLIENTE_ID_DB_NAME + " = C." + Cuenta.CLIENTE_CODIGO_DB_NAME
            + " WHERE T." + Transaccion.MONTO_DB_NAME + ">? && CL." + Cliente.CLIENTE_ID_DB_NAME + " = ?";
    private final String OBTENER_TRANSACCIONES_REPORTE6 = "SELECT T.*,CL." + Cliente.CLIENTE_ID_DB_NAME + " AS codigo_cliente FROM " + Transaccion.TRANSACCION_DB_NAME + " T INNER JOIN " + Cuenta.CUENTA_DB_NAME + " C "
            + "ON T." + Transaccion.CUENTA_CODIGO_DB_NAME + " = C." + Cuenta.CUENTA_ID_DB_NAME + " INNER JOIN " + Cliente.CLIENTE_DB_NAME + " CL ON CL." + Cliente.CLIENTE_ID_DB_NAME + " = C." + Cuenta.CLIENTE_CODIGO_DB_NAME
            + " WHERE CL." + Cliente.CLIENTE_ID_DB_NAME + " = ?";
    private final String OBTENER_TRANSACCIONES_CUENTA = "SELECT * FROM "+Transaccion.TRANSACCION_DB_NAME+" WHERE "+Transaccion.CUENTA_CODIGO_DB_NAME+"=?";

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

    /**
     * Obtenemos las transacciones de un cliente en especifico que sean mayores
     * al limite establecido
     *
     * @param limite
     * @param cliente_codigo
     * @return
     */
    public ArrayList<Transaccion> obtenerTransacciones(double limite, long cliente_codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_TRANSACCIONES_REPORTE2);
            preSt.setDouble(1, limite);
            preSt.setLong(2, cliente_codigo);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            System.out.println("Error al obtener las transacciones para reporte 2 " + e);
            return null;
        }
    }

    
    /**
     * Obtener todas las transacciones de un cliente en especifico 
     * @param limite
     * @param cliente_codigo
     * @return 
     */
    public ArrayList<Transaccion> obtenerTransacciones(long cliente_codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_TRANSACCIONES_REPORTE6);
            preSt.setLong(1, cliente_codigo);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            System.out.println("Error al obtener las transacciones " + e);
            return null;
        }
    }
    
    /**
     * Obtenemos las transacciones de una cuenta en especifico
     * al limite establecido
     *
     * @param limite
     * @param cliente_codigo
     * @return
     */
    public ArrayList<Transaccion> obtenerTransaccionesCuenta(long cuenta_codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_TRANSACCIONES_CUENTA);
            preSt.setLong(1, cuenta_codigo);

            ArrayList<Transaccion> listaTransacciones = new ArrayList<>();
            Transaccion transaccion = null;

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                transaccion = new Transaccion(
                        result.getLong(Transaccion.CODIGO_DB_NAME),
                        result.getDate(Transaccion.FECHA_DB_NAME),
                        result.getTime(Transaccion.HORA_DB_NAME),
                        result.getString(Transaccion.TIPO_DB_NAME),
                        result.getDouble(Transaccion.MONTO_DB_NAME),
                        result.getLong(Transaccion.CUENTA_CODIGO_DB_NAME),
                        result.getLong(Transaccion.CAJERO_CODIGO_DB_NAME)
                );
                listaTransacciones.add(transaccion);
            }
            return listaTransacciones;

        } catch (SQLException e) {
            System.out.println("Error al obtener las transacciones para reporte 2 " + e);
            return null;
        }
    }

}
