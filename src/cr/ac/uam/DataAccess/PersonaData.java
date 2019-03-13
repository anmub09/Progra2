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

/**
 *
 * @author fabi
 */
public class PersonaData implements IPersonaData {

    public boolean grabarEmpleado(Empleado empleado) throws EmpleadoException{
        String personaLinea = mapeaPersona(empleado);
        String empleadoLinea = mapeaEmpleado(empleado);
        boolean grabaArchivoPersona = UtilidadesArchivos.grabaArchivo(ARCHIVO_PERSONAS, personaLinea);
        boolean grabaArchivoEmpleado = UtilidadesArchivos.grabaArchivo(ARCHIVO_EMPLEADOS, empleadoLinea);
        return grabaArchivoEmpleado && grabaArchivoPersona;
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
        return builder.toString();
    }

    @Override
    public Empleado login(String login) throws EmpleadoException {
        return mapeEmpleado(UtilidadesArchivos.buscarEnArchivo(ARCHIVO_EMPLEADOS, login));
    }

    private Empleado mapeEmpleado(String lineaPersona) throws EmpleadoException {
      
        try {
            String[] arrayPersona = lineaPersona.split(TOKEN);
            Empleado empleado = new Empleado();
            empleado.setApellidoMaterno(arrayPersona[0]);
            empleado.setApellidoPaterno(arrayPersona[1]);
            empleado.setEstadoCivil(Enumerados.EstadoCivil.valueOf(arrayPersona[2]));
            empleado.setFechaNacimiento(new Date(arrayPersona[3]));
            empleado.setNombre(arrayPersona[4]);
            empleado.setNumeroCedula(arrayPersona[5]);

            String lineaEmpleado = UtilidadesArchivos.buscarEnArchivo(ARCHIVO_EMPLEADOS, empleado.getNumeroCedula());
            String[] arrayEmpleado = lineaEmpleado.split(TOKEN);
            empleado.setLogin(arrayEmpleado[1]);
            empleado.setNumeroEmpleado(arrayEmpleado[2]);
            empleado.setPassword(arrayEmpleado[2]);
            empleado.setTipoEmpleado(Enumerados.TipoEmpleado.valueOf(arrayEmpleado[3]));
            return empleado;

        } catch (Exception e) {
            throw new EmpleadoException("Problemas al mapear el empleado intente de nuevo.... ");
        }
    }
}
