package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.widget.TextView;

/**
 * Clase Holder.
 * Se ha creado para optimizar el proceso de inflar vistas en la clase {@link AdaptadorContactos AdaptadorContactos}.
 * Utilizando esta clase para almacenar los textView nos ahorramos el consumo que supone acceder a la clase R a sacar los identificadores
 * de cada uno de los textView.
 */
public class MensajesHolder {
    /**
     * Campo donde se almacena el TextView correspondiente al nombre del destinatario del mensaje.
     */
    TextView destinatario;
    /**
     * Campo donde se almacena el TextView correspondiente al nombre del remitente del mensaje.
     */
    TextView remitente;
    /**
     * Campo donde se almacena el TextView correspondiente al asunto del mensaje.
     */
    TextView asunto;
    /**
     * Campo donde se almacena el TextView correspondiente al cuerpo del mensaje.
     */
    TextView cuerpo;
    /**
     * Campo donde se almacena el TextView correspondiente a la hora del mensaje.
     */
    TextView hora;
}
