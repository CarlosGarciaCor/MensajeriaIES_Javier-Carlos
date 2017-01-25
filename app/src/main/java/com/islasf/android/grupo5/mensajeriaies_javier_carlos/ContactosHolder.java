package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.widget.TextView;

/**
 * Clase Holder.
 * Se ha creado para optimizar el proceso de inflar vistas en la clase {@link AdaptadorContactos AdaptadorContactos}.
 * Utilizando esta clase para almacenar los textView nos ahorramos el consumo que supone acceder a la clase R a sacar los identificadores
 * de cada uno de los textView.
 */
public class ContactosHolder {
    TextView nombre;
    TextView numero;
    TextView email;
}
