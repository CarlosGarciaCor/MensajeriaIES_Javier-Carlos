package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

/**
* Interfaz para implementar el callback de ContactosFragment en ContactosActivity
 * @author Carlos García y Javier Sánchez
 */

public interface ContactosListeners {

    /**
     * Método a implementar lo que sucede al seleccionar un contacto de la lista
     * @param contacto Requiere el contacto seleccionado
     */
    public void onSelectContacto(Contacto contacto);

}
