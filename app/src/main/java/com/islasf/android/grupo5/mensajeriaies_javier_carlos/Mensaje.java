package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by sanch on 22/01/2017.
 */

public class Mensaje{

    private String asunto;
    private String cuerpoMensaje;
    private Contacto destinatario;
    private Contacto remitente;
    private boolean illCall;
    private boolean callMe;
    private final int ASUNTO_MAX_LENGTH=30;
    private Context context;

    public Mensaje(Context context){
        this.context=context;
    };

    public Mensaje(Contacto remitente, Contacto destinatario, String asunto, String cuerpoMensaje, boolean illCall, boolean callMe){
        this.remitente=remitente;
        this.destinatario=destinatario;
        this.asunto=asunto;
        this.cuerpoMensaje=cuerpoMensaje;
        this.illCall=illCall;
        this.callMe=callMe;

        modelarMensaje();
    }


    private void modelarMensaje(){
        String asuntodefinitivo = "";
        /*
         TODO añadir al asunto del mensaje todo el rollo sobre si es urgente y tal.
         Ejemplos:
            [URGENTE]Aplazamiento de los muertos.
            [INFO]Ábrete una cuenta en nuestros cojones.

         En cuanto al cuerpo:
            Mensaje:
                Me coméis la polla.
            Remitente:
                El remitente.
            Especificaciones:
                Volverá a llamar / Llama tú puto gordo.

            Mensaje enviado utilizando el servicio de mensajería del IES Islas Filipinas.



            COSA GORDSAAAAAA! Esto en los email muy bonitos pero en los sms estamos limitated xdd
        */

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
        //if (!remitente.isValid()) return false;
        //if (!illCall && !callMe) return false;
        if (cuerpoMensaje.equals("")) return false;
        if (asunto.equals("") || asunto.length()>ASUNTO_MAX_LENGTH) return false;

        return true;
    }

    public boolean enviarMensajeEmail(){

       // if (!validarMensajeEmail())return false;
        //TODO: programar aquí el envío de email. True si se envía correctamente
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL, new String[]{destinatario.getEmail()});
        i.putExtra(Intent.EXTRA_SUBJECT, this.asunto);
        i.putExtra(Intent.EXTRA_TEXT, this.cuerpoMensaje);
        context.startActivity(Intent.createChooser(i, "Enviar correo..."));

        return true;

    }

    public boolean enviarMensajeSMS(){

        if (!validarMensajeSMS()) return false;
            //TODO: programar aquí el envío de SMS. True si se envía correctamente
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + destinatario.getTelefono()));
            i.putExtra("sms_body", this.cuerpoMensaje);
            context.startActivity(Intent.createChooser(i, "Enviar sms..."));
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
