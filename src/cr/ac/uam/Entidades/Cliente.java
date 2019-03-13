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
public final class Cliente extends Persona{
    private Enumerados.TipoCliente tipoCliente;
    private String numeroCliente;

    public Cliente(Enumerados.TipoCliente tipoCliente, String numeroCliente, String nombre, String apellidoPaterno, String apellidoMaterno, Date fechaNacimiento, Enumerados.EstadoCivil estadoCivil, String numeroCedula) {
        super(nombre, apellidoPaterno, apellidoMaterno, fechaNacimiento, estadoCivil, numeroCedula);
        this.tipoCliente = tipoCliente;
        this.numeroCliente = numeroCliente;
    }
    
    /**
     * @return the tipoCliente
     */
    public Enumerados.TipoCliente getTipoCliente() {
        return tipoCliente;
    }

    /**
     * @param tipoCliente the tipoCliente to set
     */
    public void setTipoCliente(Enumerados.TipoCliente tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    /**
     * @return the numeroCliente
     */
    public String getNumeroCliente() {
        return numeroCliente;
    }

    /**
     * @param numeroCliente the numeroCliente to set
     */
    public void setNumeroCliente(String numeroCliente) {
        this.numeroCliente = numeroCliente;
    }
   

    
}
