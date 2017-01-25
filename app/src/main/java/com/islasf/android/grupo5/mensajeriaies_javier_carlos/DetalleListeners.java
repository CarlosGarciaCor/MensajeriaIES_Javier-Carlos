package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

/**
 * Interfaz para implementar el callback de DetalleFragment en DetalleActivity
 * @author Carlos García y Javier Sánchez
 */

public interface DetalleListeners {

    /**
     * Método donde se implementa lo que sucede al seleccionar un destinatario
     */
    public void onSeleccionarDestinatario();

    /**
     * Método donde se implementa lo que sucede al selecionar un remitente
     */
    public void onSeleccionarRemitente();

    /**
     * Método donde se implementa lo que sucede al enviar un SMS
     */
    public void onEnviarSMS();

    /**
     * Método donde se implementa lo que sucede al enviar un Email
     */
    public void onEnviarEmail();

}
