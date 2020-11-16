/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class CuentaModel {

    private final String CREAR_CUENTA_SIN_CODIGO = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.FECHA_CREACION_DB_NAME + "," + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?)";
    private final String CREAR_CUENTA_CON_CODIGO = "INSERT INTO " + Cuenta.CUENTA_DB_NAME + " (" + Cuenta.CUENTA_ID_DB_NAME + "," + Cuenta.FECHA_CREACION_DB_NAME + "," + Cuenta.MONTO_DB_NAME + "," + Cuenta.CLIENTE_CODIGO_DB_NAME + ") VALUES (?,?,?,?)";
    private final String OBTENER_CUENTA = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE " + Cuenta.CUENTA_ID_DB_NAME + "=? && " + Cuenta.CLIENTE_CODIGO_DB_NAME + "!=?";
    private final String OBTENER_CUENTA_ESPECIFICA = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE " + Cuenta.CUENTA_ID_DB_NAME + "=?";
    private final String OBTENER_CUENTAS = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + "=? ";
    private final String OBTENER_CUENTAS_FILTRANDO = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + "=? && " + Cuenta.CUENTA_ID_DB_NAME + " LIKE CONCAT ('%',?,'%')";

    private static Connection connection = Conexion.getInstance();
    TransaccionModel transaccionModel = new TransaccionModel();

    /**
     * Agregamos una nueva cuenta desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param cuenta
     * @return
     * @throws SQLException
     */
    public long agregarCuentaArchivo(Cuenta cuenta) throws SQLException {
        try {

            PreparedStatement preSt = connection.prepareStatement(CREAR_CUENTA_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

            preSt.setLong(1, cuenta.getCodigo());
            preSt.setDate(2, cuenta.getFecha_creacion());
            preSt.setDouble(3, cuenta.getMonto());
            preSt.setLong(4, cuenta.getCliente_codigo());

            preSt.executeUpdate();
            Transaccion transaccion = new Transaccion(0, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "CREDITO", cuenta.getMonto(), cuenta.getCodigo(), 101);
            transaccionModel.agregarTransaccion(transaccion);
            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (SQLException e) {
            System.out.println("Error en cuenta " + e);
        }

        return -1;
    }

    /**
     * Agregamos una nueva cuenta, al completar la insercion devuelve el codigo
     * autogenerado.
     *
     * @param cuenta
     * @return
     * @throws SQLException
     */
    public long agregarCuenta(Cuenta cuenta) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(CREAR_CUENTA_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, cuenta.getFecha_creacion());
            preSt.setDouble(2, cuenta.getMonto());
            preSt.setLong(3, cuenta.getCliente_codigo());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en cuenta " + e);
        }

        return -1;
    }

    /**
     * Obtiene cuenta ingresada por el cliente al enviar solicitud de asociacion
     *
     * @param codigo
     * @return
     */
    public Cuenta obtenerCuenta(long codigo, long cliente_codigo) {

        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTA);
            preSt.setLong(1, codigo);
            preSt.setLong(2, cliente_codigo);

            Cuenta cuenta = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cuenta = new Cuenta(
                        rs.getLong(Cuenta.CUENTA_ID_DB_NAME),
                        rs.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                        rs.getDouble(Cuenta.MONTO_DB_NAME),
                        rs.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
                );
            }
            return cuenta;

        } catch (SQLException e) {
            System.out.println("Cuenta no existe " + e);
            return null;
        }
    }

    /**
     * Se obtiene una cuenta en especifico segun su codigo
     *
     * @param codigo
     * @param cliente_codigo
     * @return
     */
    public Cuenta obtenerCuenta(long cuenta_codigo) {

        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTA_ESPECIFICA);
            preSt.setLong(1, cuenta_codigo);

            Cuenta cuenta = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cuenta = new Cuenta(
                        rs.getLong(Cuenta.CUENTA_ID_DB_NAME),
                        rs.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                        rs.getDouble(Cuenta.MONTO_DB_NAME),
                        rs.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
                );
            }
            return cuenta;

        } catch (SQLException e) {
            System.out.println("Cuenta no existe " + e);
            return null;
        }
    }

    /**
     * Obtiene las cuentas de un cliente en especifico
     *
     * @param cliente_codigo
     * @return
     */
    public ArrayList<Cuenta> obtenerCuentas(long cliente_codigo) {

        try {
            PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTAS);
            preSt.setLong(1, cliente_codigo);

            ArrayList<Cuenta> listaCuentas = new ArrayList<>();
            Cuenta cuenta = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cuenta = new Cuenta(
                        rs.getLong(Cuenta.CUENTA_ID_DB_NAME),
                        rs.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                        rs.getDouble(Cuenta.MONTO_DB_NAME),
                        rs.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
                );
                listaCuentas.add(cuenta);
            }
            return listaCuentas;

        } catch (SQLException e) {
            System.out.println("Cuenta no existe " + e);
            return null;
        }
    }

    public ArrayList<Cuenta> obtenerCuentasFiltrando(long cliente_codigo, String cuenta_codigo) {

        try {
            long cuenta_codigoL = Long.parseLong(cuenta_codigo);
            PreparedStatement preSt = connection.prepareStatement(OBTENER_CUENTAS_FILTRANDO);
            preSt.setLong(1, cliente_codigo);
            preSt.setLong(2, cuenta_codigoL);

            ArrayList<Cuenta> listaCuentas = new ArrayList<>();
            Cuenta cuenta = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cuenta = new Cuenta(
                        rs.getLong(Cuenta.CUENTA_ID_DB_NAME),
                        rs.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                        rs.getDouble(Cuenta.MONTO_DB_NAME),
                        rs.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
                );
                listaCuentas.add(cuenta);
            }
            return listaCuentas;

        } catch (NumberFormatException | SQLException e) {
            System.out.println("Cuenta no existe " + e);
            return null;
        }
    }

}
