package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Actividad asociada al fragmento del historial de mensajes.
 * No tiene ninguna funcionalidad, por lo que sólo vamos a asociarle su
 * layout (que hará referencia al fragment)
 */
public class HistorialActivity extends AppCompatActivity {

    /**
     * En el onCreate no hacemos nada, sólo establecer el ContentView con el layout de la actividad.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
    }
}
