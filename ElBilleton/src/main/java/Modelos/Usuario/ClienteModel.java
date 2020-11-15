/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos.Usuario;

import Clases.DuplicarPDF;
import Clases.Encriptador;
import Modelos.Conexion.Conexion;
import Modelos.Historial.HistorialClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Banco.Transaccion;
import Objetos.Usuarios.Cliente;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author lex
 */
public class ClienteModel {

    //Constantes estaticas para base de datos
    private final String CLIENTES = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME;
    private final String CLIENTES_FILTRO = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CLIENTE_ID_DB_NAME + " LIKE CONCAT('%',?,'%')";
    private final String BUSCAR_CLIENTE = "SELECT * FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CLIENTE_ID_DB_NAME + " =?";
    private final String CREAR_CLIENTE_SIN_CODIGO = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.NOMBRE_DB_NAME + "," + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME
            + "," + Cliente.FECHA_NACIMIENTO_DB_NAME + "," + Cliente.PDFDPI_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?)";
    private final String CREAR_CLIENTE_CON_CODIGO = "INSERT INTO " + Cliente.CLIENTE_DB_NAME + " (" + Cliente.CLIENTE_ID_DB_NAME + "," + Cliente.NOMBRE_DB_NAME + ","
            + Cliente.DPI_DB_NAME + "," + Cliente.DIRECCION_DB_NAME + "," + Cliente.SEXO_DB_NAME + "," + Cliente.FECHA_NACIMIENTO_DB_NAME + "," + Cliente.PDFDPI_DB_NAME + "," + Cliente.PASSWORD_DB_NAME + ") VALUES (?,?,?,?,?,?,?,?)";
    private final String DPI_CLIENTE = "SELECT " + Cliente.PDFDPI_DB_NAME + " FROM " + Cliente.CLIENTE_DB_NAME + " WHERE " + Cliente.CLIENTE_ID_DB_NAME + "= ?";
    private final String ACTUALIZAR_CLIENTE = "UPDATE " + Cliente.CLIENTE_DB_NAME + " SET " + Cliente.NOMBRE_DB_NAME + "=?,"
            + Cliente.FECHA_NACIMIENTO_DB_NAME + "=?," + Cliente.DPI_DB_NAME + "=?," + Cliente.DIRECCION_DB_NAME + "=?," + Cliente.SEXO_DB_NAME + "=?,"
            + Cliente.PASSWORD_DB_NAME + "=?," + Cliente.PDFDPI_DB_NAME + "=? WHERE " + Cliente.CLIENTE_ID_DB_NAME + " =?";
    private final String REPORTE_2 = "SELECT C.* FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo INNER JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo WHERE T.monto>? GROUP BY C.codigo";
    private final String REPORTE_3 = "SELECT C.*,SUM(T.monto) AS monto FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo INNER JOIN " + Transaccion.TRANSACCION_DB_NAME + " T ON T.cuenta_codigo=CU.codigo GROUP BY C.codigo HAVING SUM(T.monto)>?";
    private final String REPORTE_4 = "SELECT C.*,SUM(CU.monto) AS monto FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN " + Cuenta.CUENTA_DB_NAME + " CU ON C.codigo=CU.cliente_codigo GROUP BY C.codigo ORDER BY monto DESC LIMIT 10";
    private final String REPORTE_5 = "SELECT * FROM "+ Cliente.CLIENTE_DB_NAME +" WHERE codigo NOT IN (SELECT C.codigo FROM " + Cliente.CLIENTE_DB_NAME + " C INNER JOIN "+ Cuenta.CUENTA_DB_NAME +" CU ON CU.cliente_codigo=C.codigo RIGHT JOIN " +Transaccion.TRANSACCION_DB_NAME +" T ON T.cuenta_codigo=CU.codigo WHERE T.fecha BETWEEN ? AND ? GROUP BY C.codigo )";
    
    private static Connection connection = Conexion.getInstance();

    HistorialClienteModel historial_cliente = new HistorialClienteModel();

    /**
     * Agregamos un nuevo cliente desde la carga de archivos, al completar la
     * insercion devuelve el codigo autogenerado.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public void agregarClienteArchivo(Cliente cliente) throws SQLException, UnsupportedEncodingException {

        try {
            DuplicarPDF crearPDF = new DuplicarPDF(cliente.getPdfDPI());

            InputStream pdf1 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());
            InputStream pdf2 = new ByteArrayInputStream(crearPDF.obtenerArrayDatos());

            PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE_CON_CODIGO, Statement.RETURN_GENERATED_KEYS);

            //Se encripta la password
            cliente.setPassword(Encriptador.encriptar(cliente.getPassword()));

            preSt.setLong(1, cliente.getCodigo());
            preSt.setString(2, cliente.getNombre());
            preSt.setString(3, cliente.getDpi());
            preSt.setString(4, cliente.getDireccion());
            preSt.setString(5, cliente.getSexo());
            preSt.setDate(6, cliente.getFecha_nacimiento());
            preSt.setBlob(7, pdf1);
            preSt.setString(8, cliente.getPassword());

            preSt.executeUpdate();
            cliente.setPdfDPI(pdf2);
            historial_cliente.agregarHistorialCliente(cliente);

        } catch (NullPointerException | UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cliente " + e);
        }
    }

    /**
     * Agregamos un nuevo cliente, al completar la insercion devuelve el codigo
     * autogenerado.
     *
     * @param cliente
     * @return
     * @throws SQLException
     */
    public long agregarCliente(Cliente cliente) throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = connection.prepareStatement(CREAR_CLIENTE_SIN_CODIGO, Statement.RETURN_GENERATED_KEYS);

            //Se encripta la password
            cliente.setPassword(Encriptador.encriptar(cliente.getPassword()));

            preSt.setString(1, cliente.getNombre());
            preSt.setString(2, cliente.getDpi());
            preSt.setString(3, cliente.getDireccion());
            preSt.setString(4, cliente.getSexo());
            preSt.setDate(5, cliente.getFecha_nacimiento());
            preSt.setBlob(6, cliente.getPdfDPI());
            preSt.setString(7, cliente.getPassword());

            preSt.executeUpdate();

            ResultSet result = preSt.getGeneratedKeys();
            if (result.first()) {
                return result.getLong(1);
            }
        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cliente " + e);
        }

        return -1;
    }

    /**
     * Metodo que busca un cliente por su codigo
     *
     * @param codigo
     * @return
     * @throws SQLException
     */
    public Cliente obtenerCliente(long codigo) throws SQLException, UnsupportedEncodingException {
        try {

            PreparedStatement preSt = connection.prepareStatement(BUSCAR_CLIENTE);
            preSt.setLong(1, codigo);
            ResultSet result = preSt.executeQuery();

            Cliente cliente = null;

            while (result.next()) {
                cliente = new Cliente(
                        result.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        result.getString(Cliente.NOMBRE_DB_NAME),
                        result.getString(Cliente.DPI_DB_NAME),
                        result.getString(Cliente.DIRECCION_DB_NAME),
                        result.getString(Cliente.SEXO_DB_NAME),
                        result.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        result.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        result.getString(Cliente.PASSWORD_DB_NAME)
                );
            }
            cliente.setPassword(Encriptador.desencriptar(cliente.getPassword()));
            return cliente;
        } catch (NullPointerException | UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cliente " + e);
        }
        return null;
    }

    /**
     * Metodo para obtener una lista con los clientes existentes
     *
     * @return
     */
    public ArrayList<Cliente> obtenerClientes() {
        try {
            ArrayList<Cliente> listaClientes = new ArrayList<>();

            PreparedStatement preSt = connection.prepareStatement(CLIENTES);
            ResultSet result = preSt.executeQuery();

            Cliente cliente = null;

            while (result.next()) {
                cliente = new Cliente(
                        result.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        result.getString(Cliente.NOMBRE_DB_NAME),
                        result.getString(Cliente.DPI_DB_NAME),
                        result.getString(Cliente.DIRECCION_DB_NAME),
                        result.getString(Cliente.SEXO_DB_NAME),
                        result.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        result.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        result.getString(Cliente.PASSWORD_DB_NAME)
                );
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error en lista de clientes " + e);
        }
        return null;
    }

    /**
     * Metodo para obtener una lista con los clientes filtrando por codigo
     *
     * @return
     */
    public ArrayList<Cliente> obtenerClientesFiltrando(long codigo) {
        try {
            ArrayList<Cliente> listaClientes = new ArrayList<>();

            PreparedStatement preSt = connection.prepareStatement(CLIENTES_FILTRO);
            preSt.setLong(1, codigo);
            ResultSet result = preSt.executeQuery();

            Cliente cliente = null;

            while (result.next()) {
                cliente = new Cliente(
                        result.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        result.getString(Cliente.NOMBRE_DB_NAME),
                        result.getString(Cliente.DPI_DB_NAME),
                        result.getString(Cliente.DIRECCION_DB_NAME),
                        result.getString(Cliente.SEXO_DB_NAME),
                        result.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        result.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        result.getString(Cliente.PASSWORD_DB_NAME)
                );
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error en lista de clientes " + e);
        }
        return null;
    }

    /**
     * Devuelve el archivo pdf del dpi
     *
     * @param codigo
     * @return
     */
    public InputStream obtenerDPI(long codigo) {
        try {
            PreparedStatement preSt = connection.prepareStatement(DPI_CLIENTE);
            preSt.setLong(1, codigo);

            ResultSet result = preSt.executeQuery();

            while (result.next()) {
                return result.getBlob(1).getBinaryStream();
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener dpi de db " + e);
            return null;
        }
        return null;
    }

    /**
     * Modificar los datos del cliente
     *
     * @param cliente
     * @param codigoCliente
     * @throws SQLException
     */
    public void modificarCliente(Cliente cliente) throws SQLException {
        try {
            cliente.setPassword(Encriptador.encriptar(cliente.getPassword()));
            PreparedStatement preSt = connection.prepareStatement(ACTUALIZAR_CLIENTE);

            preSt.setString(1, cliente.getNombre());
            preSt.setDate(2, cliente.getFecha_nacimiento());
            preSt.setString(3, cliente.getDpi());
            preSt.setString(4, cliente.getDireccion());
            preSt.setString(5, cliente.getSexo());
            preSt.setString(6, cliente.getPassword());
            preSt.setBinaryStream(7, cliente.getPdfDPI());
            preSt.setLong(8, cliente.getCodigo());
            preSt.executeUpdate();

        } catch (UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en actualizar " + e);
        }

    }

    /**
     * Verifica que usuario exista y que la password sea correctas
     *
     * @param codigo
     * @param password
     * @return
     * @throws SQLException
     */
    public Cliente validacionLogin(long codigo, String password) throws SQLException, UnsupportedEncodingException {
        try {

            Cliente cliente = obtenerCliente(codigo);
            if (cliente != null && cliente.getPassword().equals(password)) {
                return cliente;
            }
        } catch (NullPointerException | UnsupportedEncodingException | SQLException e) {
            System.out.println("Error en cliente " + e);
        }
        return null;
    }

    public ArrayList<Cliente> conTransaccionesMayores(Double limite) {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_2);
            preSt.setDouble(1, limite);

            ArrayList<Cliente> listaClientes = new ArrayList<>();
            Cliente cliente = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        rs.getString(Cliente.NOMBRE_DB_NAME),
                        rs.getString(Cliente.DPI_DB_NAME),
                        rs.getString(Cliente.DIRECCION_DB_NAME),
                        rs.getString(Cliente.SEXO_DB_NAME),
                        rs.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        rs.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        rs.getString(Cliente.PASSWORD_DB_NAME)
                );
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes del reporte 2 " + e);
            return null;
        }
    }

    /**
     * Obtener los clientes con la suma de transacciones que pase el limite
     *
     * @param limite
     * @return
     */
    public ArrayList<Cliente> conTransaccionesSumadasMayores(Double limite) {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_3);
            preSt.setDouble(1, limite);

            ArrayList<Cliente> listaClientes = new ArrayList<>();
            Cliente cliente = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        rs.getString(Cliente.NOMBRE_DB_NAME),
                        rs.getString(Cliente.DPI_DB_NAME),
                        rs.getString(Cliente.DIRECCION_DB_NAME),
                        rs.getString(Cliente.SEXO_DB_NAME),
                        rs.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        rs.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        rs.getDouble(Cliente.SUMA_DB_NAME)
                );
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes del reporte 3 " + e);
            return null;
        }
    }

    public ArrayList<Cliente> conMasDinero() {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_4);

            ArrayList<Cliente> listaClientes = new ArrayList<>();
            Cliente cliente = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        rs.getString(Cliente.NOMBRE_DB_NAME),
                        rs.getString(Cliente.DPI_DB_NAME),
                        rs.getString(Cliente.DIRECCION_DB_NAME),
                        rs.getString(Cliente.SEXO_DB_NAME),
                        rs.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        rs.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        rs.getDouble(Cliente.SUMA_DB_NAME)
                );
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes del reporte 3 " + e);
            return null;
        }
    }
    
     public ArrayList<Cliente> sinTransacciones(Date fecha1, Date fecha2) {
        try {
            PreparedStatement preSt = connection.prepareStatement(REPORTE_5);
            preSt.setDate(1,fecha1);
            preSt.setDate(2,fecha2);

            ArrayList<Cliente> listaClientes = new ArrayList<>();
            Cliente cliente = null;

            ResultSet rs = preSt.executeQuery();

            while (rs.next()) {
                cliente = new Cliente(
                        rs.getLong(Cliente.CLIENTE_ID_DB_NAME),
                        rs.getString(Cliente.NOMBRE_DB_NAME),
                        rs.getString(Cliente.DPI_DB_NAME),
                        rs.getString(Cliente.DIRECCION_DB_NAME),
                        rs.getString(Cliente.SEXO_DB_NAME),
                        rs.getDate(Cliente.FECHA_NACIMIENTO_DB_NAME),
                        rs.getBinaryStream(Cliente.PDFDPI_DB_NAME),
                        rs.getString(Cliente.PASSWORD_DB_NAME)
                );
                listaClientes.add(cliente);
            }
            return listaClientes;

        } catch (SQLException e) {
            System.out.println("Error al obtener clientes del reporte 5 " + e);
            return null;
        }
    }
}
