/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cr.ac.uam.Entidades;

import java.util.Date;

/**
 *
 * @author fabi
 */
public class Empleado extends Persona{
    private String numeroEmpleado;
    private Enumerados.TipoEmpleado tipoEmpleado;
    private String login;
    private String password;

    public Empleado() {
    }

    public Empleado(String numeroEmpleado, Enumerados.TipoEmpleado tipoEmpleado, String login, String password) {
        this.numeroEmpleado = numeroEmpleado;
        this.tipoEmpleado = tipoEmpleado;
        this.login = login;
        this.password = password;
    }

    public Empleado(String numeroEmpleado, Enumerados.TipoEmpleado tipoEmpleado, String login, String password, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Enumerados.EstadoCivil estadoCivil, String numeroCedula) {
        super(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, estadoCivil, numeroCedula);
        this.numeroEmpleado = numeroEmpleado;
        this.tipoEmpleado = tipoEmpleado;
        this.login = login;
        this.password = password;
    }
    /**
     * @return the numeroEmpleado
     */
    public String getNumeroEmpleado() {
        return numeroEmpleado;
    }

    /**
     * @param numeroEmpleado the numeroEmpleado to set
     */
    public void setNumeroEmpleado(String numeroEmpleado) {
        this.numeroEmpleado = numeroEmpleado;
    }

    /**
     * @return the tipoEmpleado
     */
    public Enumerados.TipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    /**
     * @param tipoEmpleado the tipoEmpleado to set
     */
    public void setTipoEmpleado(Enumerados.TipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}
