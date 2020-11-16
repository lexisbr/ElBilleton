/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Banco;

import Modelos.Conexion.Conexion;
import Objetos.Banco.Solicitud;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author lex
 */
public class SolicitudModel {

    private final String AGREGAR_SOLICITUD = "INSERT INTO " + Solicitud.SOLICITUD_DB_NAME
            + " (" + Solicitud.FECHA_DB_NAME + "," + Solicitud.ESTADO_DB_NAME + "," + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + "," + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + ") "
            + "VALUES (?,?,?,?) ";
    private final String COUNT_SOLICITUDES = "SELECT COUNT(*) AS count FROM " + Solicitud.SOLICITUD_DB_NAME + " WHERE " + Solicitud.CUENTA_ENVIA_CODIGO_DB_NAME + "=? && " + Solicitud.CUENTA_RECIBE_CODIGO_DB_NAME + "=? && " + Solicitud.ESTADO_DB_NAME + "=?";

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

    public int obtenerCount(long cuenta_envia, long cuenta_recibe, String estado) {
        try {
            PreparedStatement preSt = connection.prepareStatement(COUNT_SOLICITUDES);
            preSt.setLong(1, cuenta_envia);
            preSt.setLong(2, cuenta_recibe);
            preSt.setString(3, estado);
            
            ResultSet rs = preSt.executeQuery();
            
            while (rs.next()) {                
                return rs.getInt("count");
            }
            return 0;
            
        } catch (SQLException e) {
            return 0;
        }
    }

}
