package com.islasf.android.grupo5.mensajeriaies_javier_carlos;


import java.util.ArrayList;

/**
 * Interfaz CallbackAsynctaskM. Ha sido desarrollada para implementar el método de Callback.
 * Su función es devolver el array de Mensajes que se carga en la clase {@link RecogidaMensajes RecogidaMensajes}.
 */
public interface CallbackAsynctaskM {
    /**
     * Método que se implementará en la clase {@link HistorialFragment HistorialFragment}. En esa clase
     * se recogerá el ArrayList de Mensajes y se pasará al adaptador.
     * <p>
     * Por otro lado, a este método le llama la clase {@link RecogidaMensajes RecogidaMensajes} para pasarle
     * el ArrayList cuando su método doInBackground termine.
     *
     * @param mensajes la colección de mensajes
     */
    void arrayListCargado(ArrayList<Mensaje> mensajes);
}
