/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.Bussiness;

import cr.ac.uam.DataAccess.IPersonaData;
import cr.ac.uam.DataAccess.PersonaData;
import cr.ac.uam.Entidades.Empleado;
import cr.ac.uam.Entidades.Enumerados;
import cr.ac.uam.Entidades.Excepciones.EmpleadoException;
import cr.ac.uam.Seguridad.Criptografia;

/**
 *
 * @author fabi
 */
public class PersonaBussinnes {

    private final IPersonaData personaDataAccess;

    public PersonaBussinnes() {
        personaDataAccess = new PersonaData();
    }

    public boolean login(String login, String password) throws EmpleadoException {
        Empleado empleado = personaDataAccess.login(login);
        if (empleado != null) {
            String valor = Criptografia.Criptografia(Enumerados.Crytpo.DESENCRIPTAR, empleado.getPassword());
            if (password.compareTo(valor) == 0) {
                return true;
            }
            throw new EmpleadoException("Contraseña no coincide");
        }
        return false;
    }

    public String grabarEmpleado(Empleado empleado) throws EmpleadoException {
        String valor = Criptografia.Criptografia(Enumerados.Crytpo.ENCRIPTAR, empleado.getPassword());
        empleado.setPassword(valor);
        return  personaDataAccess.grabarEmpleado(empleado);
    }

}
