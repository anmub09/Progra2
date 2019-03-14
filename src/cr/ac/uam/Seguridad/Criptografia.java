/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.Seguridad;

import cr.ac.uam.Entidades.Enumerados.Crytpo;
import java.security.*;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 *
 * @author fabi
 */
public class Criptografia {

    public static final String CLAVE = "FooBar1234567890";//128 BITS 

    private static String encriptar(String clave, byte[] iv, String value) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec sks = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.ENCRYPT_MODE, sks, new IvParameterSpec(iv));

            byte[] encriptado = cipher.doFinal(value.getBytes());
            return DatatypeConverter.printBase64Binary(encriptado);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String decriptar(String clave, byte[] vector, String encriptado) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec secretKeySpec = new SecretKeySpec(clave.getBytes("UTF-8"), "AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(vector));

            byte[] dec = cipher.doFinal(DatatypeConverter.parseBase64Binary(encriptado));
            String s = new String(dec);
            return s;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static String Criptografia(Crytpo cripto, String valor) {
        String salida = "";
        byte[] vector = llenaArray();

        switch (cripto) {
            case DESENCRIPTAR: {
               return  decriptar(CLAVE, vector, valor);
            }
            case ENCRIPTAR: {
                return encriptar(CLAVE, vector, valor);
            }
        }
        return salida;
    }

    private static byte[] llenaArray() {
        byte[] vector = new byte[16];
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (byte) i;
        }
        return vector;
    }

}
