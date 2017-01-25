package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import java.io.Serializable;

/**
 * Clase Mensaje que engloba los datos necesarios para el envío de un mail o un SMS.
 * Tiene dos relaciones con la clase Contacto, a la que tendrá acceso para conocer los datos de destinatario
 * y remitente.
 *
 * Esta clase no se encarga de enviar el mensaje, sino de prepararlo para ser enviado. (Un intent implícito requiere lanzar una actividad, lo cual requiere un contexto).
 * Aquí se valida el mensaje y incluimos la opción de automatizarlo, generar el cuerpo del mensaje automáticamente
 * en función de los atributos de la instancia de esta clase.
 *
 * Implementa Serializable para poder guardarla en el Bundle que nos va a permitir guardar y restaurar el estado de la
 * actividad DetalleActivity al rotar la pantalla.
 * @author Carlos García y Javier Sánchez
 */

public class Mensaje implements Serializable{

    /** Version UID recomendable en las clases que implementan Serializable*/
    private static final long serialVersionUID=2L;
    /** Asunto del mensaje (sólo para correos)*/
    private String asunto;
    /** Cuerpo del mensaje*/
    private String cuerpoMensaje;
    /** Contacto que engloba los datos del destinatario*/
    private Contacto destinatario;
    /** Contacto que engloba los datos del remitente*/
    private Contacto remitente;
    /** Boolean que indica si el remitente volverá a llamar*/
    private boolean volveraALlamar;
    /** Boolean que indica si el remitente desea que le devuelvan la llamada*/
    private boolean deseaQueLeLlamen;
    /** Boolean que indica si el mensaje es urgente o no */
    private boolean urgent;
    /** Hora a la que se envía el mensaje */
    private String hora;
    /** Longitud máxima de un asunto de correo electrónico. Normalmente fijada en 50 caracteres.*/
    private final int ASUNTO_MAX_LENGTH=50;
    /** Longitud máxima de un SMS, fijada en 140 caracteres.*/
    private final int SMS_MAX_LENGTH=140;

    /** Constructor vacío, los datos se asignarán a partir de setters*/
    public Mensaje(){}

    /**
     * Método que construye tanto asunto como cuerpo del mensaje a enviar de forma automática
     * en función del valor que tengan los demás campos.
     */
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
            cuerpoMensaje=cuerpoMensaje+"Puede ponerse en contacto mediante: ";
            if (!remitente.getTelefono().equals("")) cuerpoMensaje=cuerpoMensaje+"\nTfno: "+remitente.getTelefono();
            if (!remitente.getEmail().equals("")) cuerpoMensaje=cuerpoMensaje+"\nMail: "+remitente.getEmail();
        }

        cuerpoMensaje=cuerpoMensaje+"\nEste mensaje ha sido generado automáticamente. Por favor no responda.";
    }

    /**
     * Método que construye el mensaje para ser enviado por SMS, ya que estos tienen una longitud máxima de 140 caracteres, además
     * de que cuesta un dinero mandarlos.
     *
     * En una variable de tipo String va generando un mensaje acortado en función de los campos de este objeto.
     * @return El mensaje modelado para ser enviado por SMS.
     */
    public String modelarSMS(){
        String mensajeSMS="";

        if (urgent)
            mensajeSMS="[URG]: ";
        else
            mensajeSMS="[Info]: ";

        if (remitente!=null) mensajeSMS=mensajeSMS+remitente.getNombre()+" ";
        else mensajeSMS="<...> ";

        if (volveraALlamar) mensajeSMS=mensajeSMS+"volverá a llamar. ";
        else mensajeSMS=mensajeSMS+"desea que le llame. ";

        if (remitente!=null && remitente.isValid()){
            mensajeSMS=mensajeSMS+"Datos contacto: ";
            if (!remitente.getTelefono().equals("")) mensajeSMS=mensajeSMS+"\nTfno: "+remitente.getTelefono();
            if (!remitente.getEmail().equals("")) mensajeSMS=mensajeSMS+"\nMail: "+remitente.getEmail();
        }

        cuerpoMensaje=mensajeSMS;
        return mensajeSMS;
    }

    /**
     * Validación previa al envío de SMS. Se valida:
     * - Que se haya seleccionado un destinatario y un remitente.
     * - Que los campos del remitente sean validos (el destinatario lo cogemos de los contactos del teléfono, suponemos que es válido siempre)
     * - Que el cuerpo del mensaje no esté vacío.
     * @return True si cumple las condiciones anteriores.
     */
    public boolean validarMensajeSMS(){
        if(destinatario==null) return false;
        if (remitente==null) return false;
        if (remitente!=null && !remitente.isValid()) return false;
        if (cuerpoMensaje.equals("")) return false;

        return true;
     }

    /**
     * Validación previa al envío de SMS. Se valida:
     * - Que se haya seleccionado un destinatario y un remitente.
     * - Que los campos del remitente sean validos (el destinatario lo cogemos de los contactos del teléfono, suponemos que es válido siempre)
     * - Que el cuerpo del mensaje no esté vacío.
     * - Que el asunto no esté vacío ni se pase del máximo de caracteres.
     * @return True si cumple las condiciones anteriores.
     */
    public boolean validarMensajeEmail(){
        if (destinatario==null) return false;
        if (remitente==null) return false;
        if (remitente!=null && !remitente.isValid()) return false;
        if (cuerpoMensaje.equals("") && cuerpoMensaje.length()>SMS_MAX_LENGTH) return false;
        if (asunto.equals("") || asunto.length()>ASUNTO_MAX_LENGTH) return false;

        return true;
    }

    //------------------------ SETTERS Y GETTERS -----------------------------

    /**
     * Getter del destinatario
     * @return Contacto destinatario
     */
    public Contacto getDestinatario() {
        return destinatario;
    }

    /**
     * Setter del destinatario
     * @param destinatario Contacto destinatario
     */
    public void setDestinatario(Contacto destinatario) {
        this.destinatario = destinatario;
    }

    /**
     * Getter del remitente
     * @return Contacto remitente
     */
    public Contacto getRemitente() {
        return remitente;
    }

    /**
     * Setter del remitente
     * @param remitente Contacto remitente
     */
    public void setRemitente(Contacto remitente) {
        this.remitente = remitente;
    }

    /**
     * Getter del cuerpo del mensaje
     * @return Cuerpo del mensaje
     */
    public String getCuerpoMensaje() {
        return cuerpoMensaje;
    }

    /**
     * Setter del cuerpo del mensaje
     * @param cuerpoMensaje Cuerpo del mensaje
     */
    public void setCuerpoMensaje(String cuerpoMensaje) {
        this.cuerpoMensaje = cuerpoMensaje;
    }

    /**
     * Getter del asunto del mensaje
     * @return Asunto del mensaje
     */
    public String getAsunto() {
        return asunto;
    }

    /**
     * Setter del asunto del mensaje
     * @param asunto Asunto del mensaje
     */
    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    /**
     * Setter para indicar que el remitente "desea que le llamen"
     */
    public void deseaQueLoLlamen() {
        this.deseaQueLeLlamen=true;
        this.volveraALlamar=false;
    }

    /**
     * Setter para indicar que el remitente "volverá a llamar"
     */
    public void volveraALlamar() {
        this.volveraALlamar=true;
        this.deseaQueLeLlamen=false;
    }

    /**
     * Setter para indicar que el mensaje es urgente
     */
    public void esUrgente() {
        this.urgent=true;
    }

    /**
     * Setter para indicar que el mensaje es sólo información
     */
    public void isJustInfo() {
        this.urgent=false;
    }

    /**
     * Getter para ver si el mensaje es urgente
     * @return True si el mensaje es urgente
     */
    public boolean isUrgent() {
        return urgent;
    }

    /**
     * Getter para ver si el remitente volverá a llamar.
     * @return True si volverá a llamar
     */
    public boolean isVolveraALlamar() {
        return volveraALlamar;
    }

    /**
     * Getter para la hora
     * @return Hora a la que se envia el mensaje
     */
    public String getHora() {
        return hora;
    }

    /**
     * Setter para la hora
     * @param hora Hora a la que se envía el mensaje
     */
    public void setHora(String hora) {
        this.hora = hora;
    }
}
