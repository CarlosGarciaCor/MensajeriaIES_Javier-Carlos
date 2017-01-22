package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

/**
 * Created by CarlosG on 22/01/2017.
 */

public class Contacto {
    private String nombre;
    private String numero;
    private String email;

    public Contacto(String email, String numero, String nombre) {
        this.email = email;
        this.numero = numero;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNumero() {
        return numero;
    }

    public String getEmail() {
        return email;
    }
}
