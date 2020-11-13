/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Modelos.Banco.CuentaModel;
import Modelos.Usuario.ClienteModel;
import Objetos.Banco.Cuenta;
import Objetos.Usuarios.Cliente;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author lex
 */
public class XMLCliente {

    private NodeList listadoCliente;
    ClienteModel clienteModel = new ClienteModel();
    CuentaModel cuentaModel = new CuentaModel();
    private String pathPdf;

    public XMLCliente(NodeList listadoCliente, String pathPdf) {
        this.listadoCliente = listadoCliente;
        this.pathPdf = pathPdf;
        clasificarEtiquetas();
    }

    public void clasificarEtiquetas() {
        try {
            Cliente cliente;
            ArrayList<Cuenta> cuentas = new ArrayList<>();

            for (int i = 0; i < listadoCliente.getLength(); i++) {
                cliente = new Cliente();
                cuentas.clear();
                // Cojo el nodo actual
                Node nodo = listadoCliente.item(i);
                // Compruebo si el nodo es un elemento
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    // Lo transformo a Element
                    Element e = (Element) nodo;
                    // Obtengo sus hijos
                    NodeList hijos = e.getChildNodes();
                    // Recorro sus hijos
                    for (int j = 0; j < hijos.getLength(); j++) {
                        // Obtengo al hijo actual
                        Node hijo = hijos.item(j);
                        NodeList hijoCuentas = hijo.getChildNodes();
                        // Compruebo si es un nodo
                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {

                            if (hijo.getNodeName().equalsIgnoreCase("CUENTAS")) {
                                cuentas = etiquetaCuentas(hijo);

                            } else {
                                crearCliente(cliente, hijo.getNodeName(), hijo.getTextContent());

                            }
                        }

                    }
                    clienteModel.agregarClienteArchivo(cliente);
                    for (int j = 0; j < cuentas.size(); j++) {
                        cuentas.get(j).setCliente_codigo(cliente.getCodigo());
                        cuentaModel.agregarCuentaArchivo(cuentas.get(j));
                    }

                }

            }

        } catch (FileNotFoundException | DOMException | SQLException e) {
            System.out.println("Error al analizar cliente " + e);
        }

    }

    public ArrayList<Cuenta> etiquetaCuentas(Node cuentas) {
        // Recorro las etiquetas
        ArrayList<Cuenta> listaCuentas = new ArrayList<>();
        listaCuentas.clear();
        System.out.println("<========>CUENTAS VARIAS DEL CLIENTE<========>");
        // Cojo el nodo actual
        Node nodo = cuentas;
        // Compruebo si el nodo es un elemento
        if (nodo.getNodeType() == Node.ELEMENT_NODE) {
            // Lo transformo a Element
            Element e = (Element) nodo;
            // Obtengo sus hijos
            NodeList hijos = e.getChildNodes();
            // Recorro sus hijos
            for (int j = 0; j < hijos.getLength(); j++) {
                // Obtengo al hijo actual
                Node hijo = hijos.item(j);

                // Compruebo si es un nodo
                if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                    // Muestro el contenido
                    if (hijo.getNodeName().equalsIgnoreCase("CUENTA")) {

                        listaCuentas = etiquetaAtributosCuentas(hijos);

                    } else {
                        System.out.println("Error al Ingresar a la Etiqueta CUENTA SINGULAR");
                    }

                }

            }
            System.out.println("");
        }

        return listaCuentas;
    }

    public ArrayList<Cuenta> etiquetaAtributosCuentas(NodeList cuentaUnica) {
        // Recorro las etiquetas
        Cuenta cuenta;
        ArrayList<Cuenta> listaCuentas = new ArrayList<>();
        listaCuentas.clear();
        System.out.println("<========>CUENTA UNICA DEL CLIENTE<========>");
        for (int i = 0; i < cuentaUnica.getLength(); i++) {
            cuenta = new Cuenta();

            // Cojo el nodo actual
            Node nodo = cuentaUnica.item(i);
            // Compruebo si el nodo es un elemento
            if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                // Lo transformo a Element
                Element e = (Element) nodo;
                // Obtengo sus hijos
                NodeList hijos = e.getChildNodes();
                // Recorro sus hijos
                for (int j = 0; j < hijos.getLength(); j++) {
                    // Obtengo al hijo actual
                    Node hijo = hijos.item(j);
                    // Compruebo si es un nodo
                    if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                        System.out.println("Etiqueta dentro de Cuenta: " + hijo.getNodeName()
                                + ", Valor: " + hijo.getTextContent());

                        crearCuenta(cuenta, hijo.getNodeName(), hijo.getTextContent());

                    }

                }
                System.out.println("AGREGO EL OBJETO CUENTA AL ARRAYLIST");
                listaCuentas.add(cuenta);
            }

        }
        return listaCuentas;
    }

    public void crearCliente(Cliente cliente, String tag, String atributo) throws FileNotFoundException {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                cliente.setCodigo(Integer.parseInt(atributo));
                break;

            case "NOMBRE":
                cliente.setNombre(atributo);
                break;

            case "DPI":
                cliente.setDpi(atributo);
                break;

            case "BIRTH":
                cliente.setFecha_nacimiento(Date.valueOf(atributo));
                break;

            case "DIRECCION":
                cliente.setDireccion(atributo);
                break;

            case "SEXO":
                cliente.setSexo(atributo);
                break;

            case "DPI-PDF":
                cliente.setPdfDPI(new FileInputStream(pathPdf + atributo));
                break;

            case "PASSWORD":
                cliente.setPassword(atributo);
                break;

            default:
        }
    }

    public void crearCuenta(Cuenta cuenta, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                cuenta.setCodigo(Long.parseLong(atributo));
                break;
            case "CREADA":
                cuenta.setFecha_creacion(Date.valueOf(atributo));
                break;
            case "CREDITO":
                cuenta.setMonto(Double.parseDouble(atributo));
                break;
            default:
                System.out.println("Etiqueta no conocida");
        }

    }

    public String getPathPdf() {
        return pathPdf;
    }

    public void setPathPdf(String pathPdf) {
        this.pathPdf = pathPdf;
    }

}
