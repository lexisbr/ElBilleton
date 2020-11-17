/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Solicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author lex
 */
public class SolicitudModel {

    private final String AGREGAR_SOLICITUD = "INSERT INTO " + Solicitud.SOLICITUD_DB_NAME
            + " (" + Solicitud.FECHA_DB_NAME + "," + Solicitud.ESTADO_DB_NAME + "," + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + "," + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + ") "
            + "VALUES (?,?,?,?) ";
    private final String COUNT_SOLICITUDES = "SELECT COUNT(*) AS count FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + "=? && " + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + "=? ";
    private final String BUSCAR_SOLICITUD_PENDIENTE = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + " = ? && " + Solicitud.ESTADO_DB_NAME + "='PENDIENTE'";
    private final String SOLICITUD_CODIGO = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CODIGO_DB_NAME + "=?";
    private final String EDITAR_SOLICITUD = "UPDATE " + Solicitud.SOLICITUD_DB_NAME + " SET " + Solicitud.FECHA_DB_NAME + "=?,"
            + Solicitud.ESTADO_DB_NAME + "=?," + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + "=?,"
            + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + "=? WHERE codigo=?";
    private final String REPORTE4_CLIENTE = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " S INNER JOIN "+Cuenta.CUENTA_DB_NAME+" C ON C."+Cuenta.CUENTA_ID_DB_NAME+"=S."+Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME+" WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME+ "=?";
    private final String REPORTE5_CLIENTE = "SELECT * FROM " + Solicitud.SOLICITUD_DB_NAME + " S INNER JOIN "+Cuenta.CUENTA_DB_NAME+" C ON C."+Cuenta.CUENTA_ID_DB_NAME+"=S."+Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME+" WHERE " + Cuenta.CLIENTE_CODIGO_DB_NAME+ "=?";
    
    private static Connection connection = Conexion.getInstance();

    /**
     * Crea una solicitud de asociacion
     *
     * @param solicitud
     */
    public void agregarSolicitud(Solicitud solicitud) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(AGREGAR_SOLICITUD);
            preSt.setDate(1, solicitud.getFecha());
            preSt.setString(2, solicitud.getEstado());
            preSt.setLong(3, solicitud.getCuenta_codigo_envia());
            preSt.setLong(4, solicitud.getCuenta_codigo_recibe());

            preSt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al crear solicitud at model " + e);
        }
    }

    /**
     * Obtiene la cantidad de solicitudes enviadas a cierta cuenta
     *
     * @param cuenta_envia
     * @param cuenta_recibe
     * @param estado
     * @return
     */
    public int obtenerCount(long cuenta_envia, long cuenta_recibe) {
        try {
            PreparedStatement preSt = connection.prepareStatement(COUNT_SOLICITUDES);
            preSt.setLong(1, cuenta_envia);
            preSt.setLong(2, cuenta_recibe);

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                return rs.getInt("count");
            }
            return 0;

        } catch (SQLException e) {
            return 0;
        }
    }

    /**
     * Realizamos una busqueda en base a el estado de las solicitudes. De no
     * existir la nos devuelve un valor null.
     *
     * @param codigoRecibe
     * @return
     * @throws SQLException
     */
    public ArrayList<Solicitud> obtenerSolicitudesPendientes(long codigoRecibe) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(BUSCAR_SOLICITUD_PENDIENTE);
        preSt.setLong(1, codigoRecibe);
        ResultSet result = preSt.executeQuery();
        ArrayList solicitudes = new ArrayList();
        Solicitud solicitud = null;
        while (result.next()) {
            solicitud = new Solicitud(
                    result.getInt(Solicitud.CODIGO_DB_NAME),
                    result.getDate(Solicitud.FECHA_DB_NAME),
                    result.getString(Solicitud.ESTADO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
            solicitudes.add(solicitud);
        }
        return solicitudes;
    }

    /**
     * Realizamos una busqueda en el codigo solicitudes. De no existir nos
     * devuelve un valor null.
     *
     * @return
     * @throws SQLException
     */
    public Solicitud obtenerSolicitudCodigo(int codigo) throws SQLException {
        PreparedStatement preSt = connection.prepareStatement(SOLICITUD_CODIGO);
        preSt.setInt(1, codigo);
        ResultSet result = preSt.executeQuery();
        Solicitud solicitud = null;
        while (result.next()) {
            solicitud = new Solicitud(
                    result.getInt(Solicitud.CODIGO_DB_NAME),
                    result.getDate(Solicitud.FECHA_DB_NAME),
                    result.getString(Solicitud.ESTADO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                    result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
            );
        }
        return solicitud;
    }

    /**
     * Editamos el la solicitud de asociacion
     *
     * @param solicitud
     * @param codigoSolicitud
     * @throws SQLException
     */
    public void actualizarAsociacion(Solicitud solicitud) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(EDITAR_SOLICITUD, Statement.RETURN_GENERATED_KEYS);

            preSt.setDate(1, solicitud.getFecha());
            preSt.setString(2, solicitud.getEstado());
            preSt.setLong(3, solicitud.getCuenta_codigo_envia());
            preSt.setLong(4, solicitud.getCuenta_codigo_recibe());
            preSt.setLong(5, solicitud.getCodigo());
            preSt.executeUpdate();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    /**
     * Obtenemos solicitudes recibidas del cliente
     *
     * @param codigoCuenta
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerSolicitudesRecibidas(long cliente_codigo) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE4_CLIENTE);
            preSt.setLong(1, cliente_codigo);
            ResultSet result = preSt.executeQuery();
            ArrayList listaSolicitudes = new ArrayList();
            Solicitud solicitud = null;
            while (result.next()) {
                solicitud = new Solicitud(
                        result.getInt(Solicitud.CODIGO_DB_NAME),
                        result.getDate(Solicitud.FECHA_DB_NAME),
                        result.getString(Solicitud.ESTADO_DB_NAME),
                        result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                        result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
                );
                listaSolicitudes.add(solicitud);
            }
            return listaSolicitudes;
            
        } catch (Exception e) {
            System.out.println("Error al obrtener las solictudes recibidas");
            return null;
        }
           
    }
    /**
     * Obtenemos solicitudes recibidas del cliente
     *
     * @param codigoCuenta
     * @return
     * @throws SQLException
     */
    public ArrayList obtenerSolicitudesEnviadas(long cliente_codigo) throws SQLException {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE5_CLIENTE);
            preSt.setLong(1, cliente_codigo);
            ResultSet result = preSt.executeQuery();
            ArrayList listaSolicitudes = new ArrayList();
            Solicitud solicitud = null;
            while (result.next()) {
                solicitud = new Solicitud(
                        result.getInt(Solicitud.CODIGO_DB_NAME),
                        result.getDate(Solicitud.FECHA_DB_NAME),
                        result.getString(Solicitud.ESTADO_DB_NAME),
                        result.getLong(Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME),
                        result.getLong(Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME)
                );
                listaSolicitudes.add(solicitud);
            }
            return listaSolicitudes;
            
        } catch (Exception e) {
            System.out.println("Error al obrtener las solictudes recibidas");
            return null;
        }
           
    }
    
    

}
