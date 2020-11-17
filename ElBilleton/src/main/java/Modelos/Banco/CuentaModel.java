/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Asociacion;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Solicitud;
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
    public final String BUSCAR_CUENTAS_CLIENTE = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE CUENTA.cliente_codigo=? && CUENTA.codigo!=?";
    private final String EDITAR_MONTO = "UPDATE " + Cuenta.CUENTA_DB_NAME + " SET " + Cuenta.MONTO_DB_NAME + "=? WHERE " + Cuenta.CUENTA_ID_DB_NAME + "=?";
    private final String CUENTA_MAS_DINERO = "SELECT * FROM " + Cuenta.CUENTA_DB_NAME + " WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME + "=? ORDER BY " + Cuenta.MONTO_DB_NAME + " DESC LIMIT 1";

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
                Transaccion transaccion = new Transaccion(0, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), "CREDITO", cuenta.getMonto(), result.getLong(1), 101);
                transaccionModel.agregarTransaccion(transaccion);
                return result.getLong(1);
            }

        } catch (SQLException e) {
            System.out.println("Error en cuenta " + e);
        }

        return -1;
    }

    /**
     * Obtiene cuenta ingresada por el cliente al enviar solicitud de asociacion
     * sin incluir las cuentas del cliente
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

    /**
     * Se obtiene las cuentas con filtro de cliente o codigo
     *
     * @param cliente_codigo
     * @param cuenta_codigo
     * @return
     */
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

    /**
     * *
     * Obenemos las cuentas excluyendo a la enviada
     *
     * @param cliente_codigo
     * @param cuenta_codigo
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerCuentasRestantes(long cliente_codigo, long cuenta_codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_CUENTAS_CLIENTE);
        preSt.setLong(1, cliente_codigo);
        preSt.setLong(2, cuenta_codigo);
        ResultSet result = preSt.executeQuery();

        ArrayList listaCuentas = new ArrayList();
        Cuenta cuenta = null;

        while (result.next()) {
            cuenta = new Cuenta(
                    result.getLong(Cuenta.CUENTA_ID_DB_NAME),
                    result.getDate(Cuenta.FECHA_CREACION_DB_NAME),
                    result.getDouble(Cuenta.MONTO_DB_NAME),
                    result.getLong(Cuenta.CLIENTE_CODIGO_DB_NAME)
            );
            listaCuentas.add(cuenta);
        }
        return listaCuentas;
    }

    /**
     * Se actualiza el monto luego de una transaccion
     *
     * @param cuenta
     * @return
     * @throws SQLException
     * @throws UnsupportedEncodingException
     */
    public void modificarMonto(Cuenta cuenta) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_MONTO);
            preSt.setDouble(1, cuenta.getMonto());
            preSt.setLong(2, cuenta.getCodigo());
            preSt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al modificar al modificar los montos");
        }

    }

    public Cuenta conMasDinero(long cliente_codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(CUENTA_MAS_DINERO);
            preSt.setLong(1, cliente_codigo);
            
            ResultSet rs = preSt.executeQuery();
            
            Cuenta cuenta = null;
            
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
            System.out.println("Error al obtener cuenta con mas dinero "+e);
            return null;
        }

    }

}
