/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Modelos.Usuario.CajeroModel;
import Objetos.Usuarios.Cajero;
import java.sql.SQLException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author lex
 */
public class XMLCajero {

    private NodeList listadoCajero;
    CajeroModel cajeroModel = new CajeroModel();

    public XMLCajero(NodeList listadoCajero) {
        this.listadoCajero = listadoCajero;
        clasificarEtiquetas();
    }

    public void clasificarEtiquetas() {
        // Recorro las etiquetas
        try {
            Cajero cajero;

            for (int i = 0; i < listadoCajero.getLength(); i++) {

                cajero = new Cajero();
                // Cojo el nodo actual
                Node nodo = listadoCajero.item(i);
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
                            crearCajero(cajero, hijo.getNodeName(), hijo.getTextContent());
                        }

                    }
                    cajeroModel.agregarCajeroArchivo(cajero);

                }

            }

        } catch (SQLException | DOMException e) {
            System.out.println("Error al analizar cajero " + e);
        }

    }

    public void crearCajero(Cajero cajero, String tag, String atributo) {

        switch (tag.toUpperCase()) {
            case "CODIGO":
                cajero.setCodigo(Long.parseLong(atributo));
                break;

            case "NOMBRE":
                cajero.setNombre(atributo);
                break;

            case "TURNO":
                 if (atributo.equalsIgnoreCase("MATUTINO")) {
                    cajero.setTurno(atributo);
                } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                    cajero.setTurno(atributo);
                } else {
                    // No se reconoce si tiene un Horario Matutino o Vespertino 
                    System.out.println("No existe turno");
                }
                break;

            case "DPI":
                cajero.setDpi(atributo);
                break;

            case "DIRECCION":
                cajero.setDireccion(atributo);
                break;

            case "SEXO":
                cajero.setSexo(atributo);
                break;

            case "PASSWORD":
                cajero.setPassword(atributo);
                break;

            default:
        }
    }

}
