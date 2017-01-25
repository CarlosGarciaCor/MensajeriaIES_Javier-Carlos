package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

/**
 * Interfaz para implementar el callback de MenuFragment en MainActivity
 * @author Carlos García y Javier Sánchez
 */

public interface MenuListeners {

    /**
     * Método donde se implementará lo que sucede al pulsar en el botón nuevo mensaje
     */
    public void onNuevoMensaje();

    /**
     * Método donde se implementará lo que sucede al pulsar en el botón Historial
     */
    public void onHistorial();

    /**
     * Método donde se implentará lo que sucede al pulsar en el botón AcercaDe
     */
    public void onAcerdaDe();

}
