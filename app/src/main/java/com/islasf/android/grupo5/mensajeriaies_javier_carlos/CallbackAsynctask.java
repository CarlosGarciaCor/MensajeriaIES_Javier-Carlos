package com.islasf.android.grupo5.mensajeriaies_javier_carlos;


import java.util.ArrayList;

/**
 * Interfaz CallbackAsynctask. Ha sido desarrollada para implementar el método de Callback.
 * Su función es devolver el array de Contactos que se carga en la clase {@link RecogidaContactos RecogidaContactos}.
 */
public interface CallbackAsynctask {
    /**
     * Método que se implementará en la clase {@link ContactosFragment ContactosFragment}. En esa clase
     * se recogerá el ArrayList de Contactos y se pasará al adaptador.
     * <p>
     * Por otro lado, a este método le llama la clase {@link RecogidaContactos RecogidaContactos} para pasarle
     * el ArrayList cuando su método doInBackground termine.
     *
     * @param contactos la colección de contactos
     */
    void arrayListCargado(ArrayList<Contacto> contactos);
}
