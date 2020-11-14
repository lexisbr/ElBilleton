/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Modelos.Usuario.ClienteModel;
import Modelos.Usuario.GerenteModel;
import Objetos.Usuarios.Gerente;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
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
public class XMLGerente {

    private NodeList listadoGerente;
    GerenteModel gerenteModel = new GerenteModel();

    public XMLGerente(NodeList listadoGerente) {
        this.listadoGerente = listadoGerente;
        clasificarEtiquetas();
    }

    public void clasificarEtiquetas() {
        // Recorro las etiquetas
        try {
            Gerente gerente;
            for (int i = 0; i < listadoGerente.getLength(); i++) {
                System.out.println("============Gerente==============");
                gerente = new Gerente();

                // Cojo el nodo actual
                Node nodo = listadoGerente.item(i);
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
                            crearGerente(gerente, hijo.getNodeName(), hijo.getTextContent());
                        }
                    }
                    gerenteModel.agregarGerenteArchivo(gerente);
                }
            }

        } catch (DOMException | SQLException | UnsupportedEncodingException | NumberFormatException e) {
            System.out.println("Error al analizar gerente " + e);
        }

    }

    public void crearGerente(Gerente gerente, String tag, String atributo) throws NumberFormatException {
        try {

            switch (tag.toUpperCase()) {
                case "CODIGO":
                    gerente.setCodigo(Long.parseLong(atributo));
                    break;

                case "NOMBRE":
                    gerente.setNombre(atributo);
                    break;

                case "TURNO":
                    if (atributo.equalsIgnoreCase("MATUTINO")) {
                        gerente.setTurno(atributo);
                    } else if (atributo.equalsIgnoreCase("VESPERTINO")) {
                        gerente.setTurno(atributo);
                    } else {
                        // No se reconoce si tiene un Horario Matutino o Vespertino 
                        System.out.println("No existe turno");
                    }
                    break;
                case "DPI":
                    gerente.setDpi(atributo);
                    break;

                case "DIRECCION":
                    gerente.setDireccion(atributo);
                    break;

                case "SEXO":
                    gerente.setSexo(atributo);
                    break;

                case "PASSWORD":
                    gerente.setPassword(atributo);
                    break;

                default:
            }
        } catch (NumberFormatException e) {
            System.out.println("Error al convertir " + e);
        }
    }

}
