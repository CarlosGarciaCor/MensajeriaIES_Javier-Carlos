package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

/**
 * Actividad asociada al fragmento del listado de contactos. En ella vamos a programar
 * qué pasa cuando seleccionas un contacto.
 */
public class ContactosActivity extends AppCompatActivity implements ContactosListeners{

    /**
     * En el onCreate le pasamos esta instancia al fragment.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        ContactosFragment fragment = (ContactosFragment) getSupportFragmentManager().findFragmentById(R.id.FrgContactos);
        fragment.setListeners(this);
    }

    /**
     * Este método va a ser el que se llame desde el fragment cuando el usuario seleccione
     * un contacto de la lista. Lo que hacemos aquí es guardar el contacto en un intent y
     * finalizar esta actividad para volver a la anterior.
     * @param contacto Requiere el contacto seleccionado
     */
    @Override
    public void onSelectContacto(Contacto contacto) {

        Intent data=new Intent();
        data.putExtra("Contacto", contacto);
        setResult(1, data);
        finish();
    }
}
