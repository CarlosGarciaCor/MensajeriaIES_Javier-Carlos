package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import java.io.Serializable;

/**
 * Created by sanch on 22/01/2017.
 */

public class Mensaje implements Serializable{

    private static final long serialVersionUID=2L;
    private String asunto;
    private String cuerpoMensaje;
    private Contacto destinatario;
    private Contacto remitente;
    private boolean volveraALlamar;
    private boolean deseaQueLeLlamen;
    private boolean justInfo;
    private boolean urgent;
    private String hora;
    private final int ASUNTO_MAX_LENGTH=50;

    public Mensaje(){}

    public Mensaje(Contacto remitente, Contacto destinatario, String asunto, String cuerpoMensaje, boolean illCall, boolean callMe){
        this.remitente=remitente;
        this.destinatario=destinatario;
        this.asunto=asunto;
        this.cuerpoMensaje=cuerpoMensaje;
        this.volveraALlamar=illCall;
        this.deseaQueLeLlamen=callMe;

        modeladoAutomatico();
    }


    public void modeladoAutomatico(){

        //Modelado de asunto
        if (urgent) asunto="[URG]: ";
        else asunto="[Info]: ";

        if (remitente!=null) asunto=asunto+remitente.getNombre()+" ";
        else asunto=asunto+"<...> ";

        asunto=asunto+"le ha dejado un mensaje";

        //Modelado de mensaje
        if (remitente!=null) cuerpoMensaje=remitente.getNombre()+" ";
        else cuerpoMensaje="<...> ";

        cuerpoMensaje=cuerpoMensaje+"le ha dejado un mensaje indicando que ";

        if (volveraALlamar) cuerpoMensaje=cuerpoMensaje+"volverá a llamar. ";
        else cuerpoMensaje=cuerpoMensaje+"desea que le llame. ";

        if (remitente!=null && remitente.isValid()){
            cuerpoMensaje=cuerpoMensaje+"Puede ponerse en contacto mediante:\n ";
            if (!remitente.getTelefono().equals("")) cuerpoMensaje=cuerpoMensaje+"Tfno: "+remitente.getTelefono();
            if (!remitente.getEmail().equals("")) cuerpoMensaje=cuerpoMensaje+"Mail: "+remitente.getEmail();
        }

        cuerpoMensaje=cuerpoMensaje+"\nEste mensaje ha sido generado automáticamente. Por favor no responda.";
    }

    //Validación previa al envío de SMS
    public boolean validarMensajeSMS(){
        if (destinatario!=null && !destinatario.isValid()) return false;
        if (remitente!=null && !remitente.isValid()) return false;
        if (!volveraALlamar && !deseaQueLeLlamen) return false;
        if (cuerpoMensaje.equals("")) return false;

        return true;
     }

    //Validación previa al envío de email
    public boolean validarMensajeEmail(){
        if (destinatario!=null && !destinatario.isValid()) return false;
        if (remitente!=null && !remitente.isValid()) return false;
        if (!volveraALlamar && !deseaQueLeLlamen) return false;
        if (cuerpoMensaje.equals("")) return false;
        if (asunto.equals("") || asunto.length()>ASUNTO_MAX_LENGTH) return false;

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

    public boolean isUrgent() {
        return urgent;
    }

    public boolean isVolveraALlamar() {
        return volveraALlamar;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }
}
