/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.DataAccess;

import java.io.*;

/**
 *
 * @author fabi
 */
public class UtilidadesArchivos {

    public static final String ARCHIVO_PERSONAS = "C:\\archivos\\personas.txt";
    public static final String ARCHIVO_EMPLEADOS = "C:\\archivos\\empleados.txt";
    public static final String ARCHIVO_CLIENTES = "C:\\archivos\\clientes.txt";
    public static final String TOKEN = ";";

    public static boolean grabaArchivo(String nombreArchivo, String linea) {
        FileWriter fichero = null;
        PrintWriter printWriter = null;
        try {
            fichero = new FileWriter(nombreArchivo);
            printWriter = new PrintWriter(fichero);
            printWriter.println(linea);

        } catch (IOException exception) {
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (IOException exception2) {
                exception2.printStackTrace();
            }
        }
        return true;
    }

    public static String buscarEnArchivo(String nombreArchivo, String criterio) {

        File archivo = null;
        FileReader fileReader = null;

        BufferedReader bufferReader = null;

        try {
            archivo = new File(nombreArchivo);
            fileReader = new FileReader(archivo);
            bufferReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferReader.readLine()) != null) {
                if (buscarEnlinea(criterio, linea)) {
                    return linea;
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        } finally {
            try {
                if (null != fileReader) {
                    fileReader.close();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        return null;

    }

    private static boolean buscarEnlinea(String criterio, String linea) {
        boolean bandera = false;
        String[] array = linea.split(";");
        for (String temporal : array) {
            if (criterio.trim().equals(temporal.trim())) {
                bandera = true;
                return bandera;
            }
        }
        return bandera;
    }
}
