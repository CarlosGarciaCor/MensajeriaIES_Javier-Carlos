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
    private boolean volveraALlamar;
    private boolean deseaQueLeLlamen;
    private boolean justInfo;
    private boolean urgent;
    private final int ASUNTO_MAX_LENGTH=50;
    private Context context;

    public Mensaje(Context context){
        this.context=context;
    };

    public Mensaje(Contacto remitente, Contacto destinatario, String asunto, String cuerpoMensaje, boolean illCall, boolean callMe){
        this.remitente=remitente;
        this.destinatario=destinatario;
        this.asunto=asunto;
        this.cuerpoMensaje=cuerpoMensaje;
        this.volveraALlamar=illCall;
        this.deseaQueLeLlamen=callMe;

        modelarAsunto();
    }


    public void modelarAsunto(){

        if (urgent) asunto="[URG]: ";
        else asunto="[Info]: ";

        if (remitente!=null) asunto=asunto+remitente.getNombre()+" ";
        else asunto=asunto+"<Alguien> ";

        if (deseaQueLeLlamen) asunto=asunto+"desea que le llame.";
        else asunto=asunto+"volverá a llamar.";
    }

    //Validación previa al envío de SMS
    private boolean validarMensajeSMS(){
        if (destinatario!=null && !destinatario.isValid()) return false;
        if (remitente!=null && !remitente.isValid()) return false;
        if (!volveraALlamar && !deseaQueLeLlamen) return false;
        if (cuerpoMensaje.equals("")) return false;

        return true;
     }

    //Validación previa al envío de email
    private boolean validarMensajeEmail(){
        if (destinatario!=null && !destinatario.isValid()) return false;
        if (remitente!=null && !remitente.isValid()) return false;
        if (!volveraALlamar && !deseaQueLeLlamen) return false;
        if (cuerpoMensaje.equals("")) return false;
        if (asunto.equals("") || asunto.length()>ASUNTO_MAX_LENGTH) return false;

        return true;
    }

    public boolean enviarMensajeEmail(){

        if (!validarMensajeEmail())return false;

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

    public void deseaQueLoLlamen() {
        this.deseaQueLeLlamen=true;
        this.volveraALlamar=false;
    }

    public void volveraALlamar() {
        this.volveraALlamar=true;
        this.deseaQueLeLlamen=false;
    }

    public void esUrgente() {
        this.urgent=true;
        this.justInfo=false;
    }

    public void isJustInfo() {
        this.justInfo=true;
        this.urgent=false;
    }
}
