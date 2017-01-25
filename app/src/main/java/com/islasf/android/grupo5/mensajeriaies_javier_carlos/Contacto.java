package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * La clase va a guardar los datos de los contactos con los que se comunicará la aplicación, tanto destinatario como remitente.
 * Los datos principales son 3: nombre, teléfono y e-mail.
 *
 * Esta clase implementa Serializable dado que es necesario mandar objectos Contacto de una actividad a otra.
 * @author Carlos García Corpas y Javier Sánchez Gómez
 */

public class Contacto implements Serializable{


     /** Serial UID recomendable al implementar Serializable */
    private static final long serialVersionUID=1L;
    /** Nombre o alias de contacto */
    private String nombre;
    /** Nombre de la empresa del contacto */
    private String empresa;
    /** Dirección de e-mail del contacto*/
    private String email;
    /** Teléfono del contacto */
    private String telefono;

    /**
     * Constructor por nombre de contacto.
     */
    public Contacto(String nombre){
        this.nombre=nombre;
    }

    /**
     * Constructor completo con todos los parámetros que definen a un contacto.
     * @param nombre Nombre de contacto
     * @param empresa Empresa del contacto
     * @param email Correo del contacto
     * @param telefono Teléfono del contacto
     */
    public Contacto(String nombre, String empresa, String email, String telefono){
        this.nombre=nombre;
        this.empresa=empresa;
        this.email=email;
        this.telefono=telefono;
    }

    /**
     * Getter del correo
     * @return Correo del contacto
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter del nombre
     * @return Nombre del contacto
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Getter del teléfono
     * @return Teléfono de contacto
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Getter de la empresa
     * @return Empresa del contacto
     */
    public String getEmpresa(){ return empresa; }

    /**
     * Método de validación de un contacto. Valida que:
     *  - El nombre no esté vacío.
     *  - El teléfono empiece por un 6 y le sigan 8 dígitos.
     *  - El email sea texto + @ + texto + .com o .es
     *
     *  Las validaciones realizadas no son exhaustivas. En una versión posterior del programa este seria un
     *  tema a tratar.
     * @return True si cumple todas las validaciones mencionadas.
     */
    public boolean isValid(){

        if (!email.matches("^.+\\@.+\\.(com|es)")) return false;
        if (nombre.equals("")) return false;
        if (!telefono.matches("^6\\d{8}")) return false;

        return true;
    }

}
