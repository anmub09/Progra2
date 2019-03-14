/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.DataAccess;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author fabi
 */
public class UtilidadesArchivos {

    public static final String ARCHIVO_PERSONAS = "C:\\archivos\\personas.txt";
    public static final String ARCHIVO_EMPLEADOS = "C:\\archivos\\empleados.txt";
    public static final String ARCHIVO_CLIENTES = "C:\\archivos\\clientes.txt";
    public static final String TOKEN = ";";
    public static final String FINAL_ARCHIVO = "&&";

    public static boolean grabaArchivo(String nombreArchivo, String linea) {
        FileWriter fichero = null;
        PrintWriter printWriter = null;
        try {

            ArrayList<String> archivoEnLineas = arregloArchivo(nombreArchivo);
            archivoEnLineas.add(linea);
            fichero = new FileWriter(nombreArchivo);
            printWriter = new PrintWriter(fichero);
            printWriter.println(arrayToString(archivoEnLineas));

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

    private static String arrayToString(ArrayList<String> lineas) {
        StringBuilder builder = new StringBuilder();
        for (String linea : lineas) {
            builder.append(linea + "\n");
        }
        return builder.toString();
    }

    private static ArrayList<String> arregloArchivo(String nombreArchivo) throws FileNotFoundException, IOException {
        ArrayList<String> lineas = new ArrayList<>();
        FileReader fileReader = new FileReader(nombreArchivo);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String linea;
        while ((bufferedReader.readLine()) != null) {
            linea = bufferedReader.readLine();
            if (linea != null) {
                lineas.add(linea);
            }

        }
        return lineas;
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
