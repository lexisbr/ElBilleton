/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.NodeList;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author lex
 */
public class LeerArchivo {
    
    XMLGerente enviarGerente;
    XMLCajero enviarCajero;
    XMLCliente enviarCliente;
    XMLTransaccion enviarTransaccion;
    
     public void dividirEtiquetas(File file, String pathArchivos) {

        try {

            // Creo una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Creo un documentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();

            // Obtengo el documento, a partir del XML
            Document documento = builder.parse(file);

            // Obtengo todas las etiquetas PADRE del documento
            NodeList listadoGerentes = documento.getElementsByTagName("GERENTE");
            NodeList listadoCajeros = documento.getElementsByTagName("CAJERO");
            NodeList listadoClientes = documento.getElementsByTagName("CLIENTE");
            NodeList listadoTransacciones = documento.getElementsByTagName("TRANSACCION");

            enviarGerente = new XMLGerente(listadoGerentes);
            enviarCajero = new XMLCajero(listadoCajeros);
            enviarCliente = new XMLCliente(listadoClientes, pathArchivos);
            enviarTransaccion = new XMLTransaccion(listadoTransacciones);
        
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
}
