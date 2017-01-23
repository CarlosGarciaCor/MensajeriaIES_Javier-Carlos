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
    private final int ASUNTO_MAX_LENGTH=30;

    public Mensaje(){};

    public Mensaje(Contacto remitente, Contacto destinatario, String asunto, String cuerpoMensaje, boolean illCall, boolean callMe){
        this.remitente=remitente;
        this.destinatario=destinatario;
        this.asunto=asunto;
        this.cuerpoMensaje=cuerpoMensaje;
        this.illCall=illCall;
        this.callMe=callMe;
    }

    //Validación previa al envío de SMS
    private boolean validarMensajeSMS(){
        if (!destinatario.isValid()) return false;
        if (!remitente.isValid()) return false;
        if (!illCall && !callMe) return false;
        if (cuerpoMensaje.equals("")) return false;

        return true;
     }

    //Validación previa al envío de email
    private boolean validarMensajeEmail(){
        if (!destinatario.isValid()) return false;
        if (!remitente.isValid()) return false;
        if (!illCall && !callMe) return false;
        if (cuerpoMensaje.equals("")) return false;
        if (asunto.equals("") || asunto.length()>ASUNTO_MAX_LENGTH) return false;

        return true;
    }

    public boolean enviarMensajeEmail(){

        if (!validarMensajeEmail())return false;
        //TODO: programar aquí el envío de SMS. True si se envía correctamente
        return true;

    }

    public boolean enviarMensajeSMS(){

        if (!validarMensajeSMS()) return false;
        //TODO: programar aquí el envío de email. True si se envía correctamente
        return true;

    }

    //------------------------ SETTERS Y GETTERS -----------------------------

    public Contacto getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Contacto destinatario) {
        this.destinatario = destinatario;
    }

    public Contacto getRemitente() {
        return remitente;
    }

    public void setRemitente(Contacto remitente) {
        this.remitente = remitente;
    }

    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public boolean isIllCall() {
        return illCall;
    }

    public void setIllCall(boolean illCall) {
        this.illCall = illCall;
    }

    public boolean isCallMe() {
        return callMe;
    }

    public void setCallMe(boolean callMe) {
        this.callMe = callMe;
    }
}
