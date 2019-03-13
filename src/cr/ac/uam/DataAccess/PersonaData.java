/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.DataAccess;

import static cr.ac.uam.DataAccess.UtilidadesArchivos.*;
import cr.ac.uam.Entidades.*;
import cr.ac.uam.Entidades.Excepciones.EmpleadoException;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author fabi
 */
public class PersonaData implements IPersonaData {

    public String grabarEmpleado(Empleado empleado) throws EmpleadoException {
        String numeroEmpleado = null;
        empleado.setNumeroEmpleado(this.numeroEmpleado());
        String personaLinea = mapeaPersona(empleado);
        String empleadoLinea = mapeaEmpleado(empleado);
        boolean grabaArchivoPersona = UtilidadesArchivos.grabaArchivo(ARCHIVO_PERSONAS, personaLinea);
        boolean grabaArchivoEmpleado = UtilidadesArchivos.grabaArchivo(ARCHIVO_EMPLEADOS, empleadoLinea);
        if (grabaArchivoEmpleado && grabaArchivoPersona) {
            numeroEmpleado = empleado.getNumeroEmpleado();
            return numeroEmpleado;
        }
        return numeroEmpleado;
    }

    private String numeroEmpleado() {
        Random aleatorio = new Random();
        String alfa = "ABCDEFGHIJKLMNOPQRSTVWXYZ";
        String cadena = "";
        int numero;
        int forma;
        forma = (int) (aleatorio.nextDouble() * alfa.length() - 1 + 0);
        numero = (int) (aleatorio.nextDouble() * 99 + 100);
        cadena = cadena + alfa.charAt(forma) + numero;

        return cadena;
    }

    private String mapeaPersona(Persona persona) {
        StringBuilder builder = new StringBuilder();
        builder.append(persona.getApellidoMaterno()).append(TOKEN);
        builder.append(persona.getApellidoPaterno()).append(TOKEN);
        builder.append(persona.getEstadoCivil()).append(TOKEN);
        builder.append(persona.getFechaNacimiento()).append(TOKEN);
        builder.append(persona.getNombre()).append(TOKEN);
        builder.append(persona.getNumeroCedula()).append(TOKEN);
        return builder.toString();
    }

    private String mapeaEmpleado(Empleado empleado) {
        StringBuilder builder = new StringBuilder();
        builder.append(empleado.getLogin()).append(TOKEN);
        builder.append(empleado.getPassword()).append(TOKEN);
        builder.append(empleado.getTipoEmpleado()).append(TOKEN);
        builder.append(empleado.getNumeroEmpleado()).append(TOKEN);
        builder.append(empleado.getNumeroCedula()).append(TOKEN);
        return builder.toString();
    }

    @Override
    public Empleado login(String login) throws EmpleadoException {
        return mapeEmpleado(UtilidadesArchivos.buscarEnArchivo(ARCHIVO_EMPLEADOS, login));
    }

    private Empleado mapeEmpleado(String lienaEmpleado) throws EmpleadoException {

        try {
            Empleado empleado=new Empleado();
            String[] arrayEmpleado = lienaEmpleado.split(TOKEN);
            empleado.setLogin(arrayEmpleado[0]);
            empleado.setPassword(arrayEmpleado[1]);
            empleado.setTipoEmpleado(Enumerados.TipoEmpleado.valueOf(arrayEmpleado[2]));
            empleado.setNumeroEmpleado(arrayEmpleado[3]);
            empleado.setNumeroCedula(arrayEmpleado[4]);
            
            String lineaPersona = UtilidadesArchivos.buscarEnArchivo(ARCHIVO_PERSONAS, empleado.getNumeroCedula());
            String[] arrayPersona = lineaPersona.split(TOKEN);
            empleado.setApellidoMaterno(arrayPersona[0]);
            empleado.setApellidoPaterno(arrayPersona[1]);
            empleado.setEstadoCivil(Enumerados.EstadoCivil.valueOf(arrayPersona[2]));
            empleado.setFechaNacimiento(new Date(arrayPersona[3]));
            empleado.setNombre(arrayPersona[4]);
            empleado.setNumeroCedula(arrayPersona[5]);

            return empleado;

        } catch (Exception e) {
            e.printStackTrace();
            throw new EmpleadoException("Problemas al mapear el empleado intente de nuevo.... ");
        }
    }
}
