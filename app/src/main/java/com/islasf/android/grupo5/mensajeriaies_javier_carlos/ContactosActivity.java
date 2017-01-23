package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class ContactosActivity extends AppCompatActivity implements ContactosListeners{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);

        ContactosFragment fragment = (ContactosFragment) getSupportFragmentManager().findFragmentById(R.id.FrgContactos);
        fragment.setListeners(this);
    }

    @Override
    public void onSelectContacto(Contacto contacto) {
        //Recogemos el contacto y volvemos al detalle

        Intent data=new Intent();
        data.putExtra("Contacto", contacto.getNombre());
        setResult(1, data);
        finish();
    }
}
