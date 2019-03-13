/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.DataAccess;

import cr.ac.uam.Entidades.Empleado;
import cr.ac.uam.Entidades.Excepciones.EmpleadoException;

/**
 *
 * @author fabi
 */
public interface IPersonaData {

    boolean grabarEmpleado(Empleado empleado)throws EmpleadoException;

    Empleado login(String login) throws EmpleadoException;
    
}
