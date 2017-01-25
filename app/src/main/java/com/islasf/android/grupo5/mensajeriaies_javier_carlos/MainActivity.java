package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Actividad Launcher de la aplicación. Es un menú que consta de tres botones, cada uno irá a una pantalla diferente.
 * En esta clase programaremos los Intents para lanzar las diversar activities de la aplicación.
 */
public class MainActivity extends AppCompatActivity implements MenuListeners {

    /**
     * En el onCreate le pasamos al fragment del menú esta instancia para que pueda hacer callback.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuFragment fragment=(MenuFragment)getSupportFragmentManager().findFragmentById(R.id.FrgMenu);
        fragment.setMenuListener(this);
    }

    /**
     * Al pulsar el botón Nuevo Mensaje ocurre esto. Lanza un Intent para
     * la actividad DetalleActivity
     */
    @Override
    public void onNuevoMensaje() {
        Intent i=new Intent(this, DetalleActivity.class);
        startActivity(i);
    }

    /**
     * Al pulsar el botón Historial ocurre esto. Lanza un Intent para
     * la actividad HistorialActivity
     */
    @Override
    public void onHistorial() {
        Intent i=new Intent(this, HistorialActivity.class);
        startActivity(i);
    }

    /**
     * Al pulsar el botón Acerca De ocurre esto. Lanza un Intent para
     * la actividad AboutActivity
     */
    @Override
    public void onAcerdaDe() {
        //TODO: intent a la actividad del fragment de lo que pollas sea esto
    }
}
