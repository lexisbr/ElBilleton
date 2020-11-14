/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 *
 * @author lex
 */
public class Encriptador {
    
    
    /*
        Metodo encriptador
    */
    public static String encriptar(String s) throws UnsupportedEncodingException{
        return Base64.getEncoder().encodeToString(s.getBytes("utf-8"));
    }
    
    /*
        Metodo desencriptador
    */
    public static String desencriptar(String s) throws UnsupportedEncodingException{
        byte[] decode = Base64.getDecoder().decode(s.getBytes());
        return new String(decode, "utf-8");
    }

}
