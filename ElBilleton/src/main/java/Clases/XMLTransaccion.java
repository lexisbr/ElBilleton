/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Modelos.Banco.TransaccionModel;
import Objetos.Banco.Transaccion;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author lex
 */
public class XMLTransaccion {

    private NodeList listadoTransaccion;
    TransaccionModel transaccionModel = new TransaccionModel();

    public XMLTransaccion(NodeList listadoTransaccion) {
        this.listadoTransaccion = listadoTransaccion;
        clasificarEtiquetas();
    }

    public void clasificarEtiquetas() {

        try {
            Transaccion transaccion;

            for (int i = 0; i < listadoTransaccion.getLength(); i++) {

                transaccion = new Transaccion();
                // Cojo el nodo actual
                Node nodo = listadoTransaccion.item(i);
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

                            System.out.println("Etiqueta: " + hijo.getNodeName()
                                    + ", Valor: " + hijo.getTextContent());
                            crearTransaccion(transaccion, hijo.getNodeName(), hijo.getTextContent());

                        }

                    }
                    transaccionModel.agregarTransaccionArchivo(transaccion);

                }

            }

        } catch (SQLException | DOMException e) {
            System.out.println("Error al analizar transaccion " + e);
        }

    }

    public void crearTransaccion(Transaccion transaccion, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                transaccion.setCodigo(Integer.parseInt(atributo));
                break;

            case "CUENTA-ID":
                transaccion.setCuenta_codigo(Long.parseLong(atributo));
                break;

            case "FECHA":
                transaccion.setFecha(Date.valueOf(atributo));
                break;

            case "HORA":
                transaccion.setHora(Time.valueOf(atributo));
                break;

            case "TIPO":
                transaccion.setTipo(atributo);
                break;

            case "MONTO":
                transaccion.setMonto(Double.parseDouble(atributo));
                break;

            case "CAJERO-ID":
                transaccion.setCajero_codigo(Long.parseLong(atributo));
                break;

            default:
        }
    }

}
