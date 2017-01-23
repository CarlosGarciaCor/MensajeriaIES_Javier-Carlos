package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.regex.Pattern;

/**
 * Created by sanch on 22/01/2017.
 */

public class Contacto implements Serializable{

    private static final long serialVersionUID=1L;
    private String nombre;
    private String email;
    private String telefono;

    public Contacto(String nombre){
        this.nombre=nombre;
    }

    //Constructor completo: NULL válido para email o teléfono, pero no los dos a la vez.
    public Contacto(String nombre, String email, String telefono){
        this.nombre=nombre;
        this.email=email;
        this.telefono=telefono;
    }

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean isValid(){

        if (!email.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$")) return false;
        if (nombre.equals("")) return false;
        if (telefono.equals(0)) return false; //TODO: buscar una buena regex para tfnos

        return true;
    }

}
