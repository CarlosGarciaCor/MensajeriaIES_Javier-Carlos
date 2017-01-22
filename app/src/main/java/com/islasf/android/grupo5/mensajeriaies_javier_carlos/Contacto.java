package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

/**
 * Created by sanch on 22/01/2017.
 */

public class Contacto {

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

}
