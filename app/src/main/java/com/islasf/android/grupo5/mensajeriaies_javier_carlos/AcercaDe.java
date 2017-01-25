package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageButton;

/**
 * Actividad que enseña los datos referentes a los programadores y el propósito de la aplicación.
 * La programación a destacar es el listener del logo, que nos lleva a la página web del instituto.
 *
 * @author Carlos García Corpas y Javier Sánchez Gómez
 */
public class AcercaDe extends Activity {

    /**
     * En el onCreate no hacemos nada, sólo establecer el ContentView con el layout de la actividad.
     * @param savedInstanceState el bundle con los posibles datos guardados de una instancia anterior.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);

    }

    /**
     * En este método controlamos que el usuario pulse en el logo del instituto, y si lo hace mandamos un
     * intent explícito para que seleccione el navegador con el que abrir la página web del mismo.
     * @param v el botón del logo
     */
    public void clickBoton(View v){
        String url = "http://ies.islasfilipinas.madrid.educa.madrid.org/";

        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setData(Uri.parse(url));
        startActivity(i);
    }

}
