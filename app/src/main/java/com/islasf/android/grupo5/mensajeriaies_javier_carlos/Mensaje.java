package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

/**
 * Created by sanch on 22/01/2017.
 */

public class Mensaje {

    private String asunto;
    private String cuerpoMensaje;
    private Contacto destinatario;
    private Contacto remitente;
    private boolean illCall;
    private boolean callMe;

    public Mensaje(Contacto remitente, Contacto destinatario, String asunto, String cuerpoMensaje, boolean illCall, boolean callMe){
        this.remitente=remitente;
        this.destinatario=destinatario;
        this.asunto=asunto;
        this.cuerpoMensaje=cuerpoMensaje;
        this.illCall=illCall;
        this.callMe=callMe;
    }

    public void enviarMensajeEmail(){

    }

    public void enviarMensajeTelefono(){

    }
}
