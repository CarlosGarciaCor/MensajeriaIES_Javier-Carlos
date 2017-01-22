package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements MenuListeners {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MenuFragment fragment=(MenuFragment)getSupportFragmentManager().findFragmentById(R.id.FrgMenu);
        fragment.setMenuListener(this);
    }

    //TODO Como dejes esto así te pego un tiro Carlos (y yo otro)
    public void diohmiowil (View v){
        Intent sifunsionabien = new Intent(this, ProbatinasContactos.class);
        startActivity(sifunsionabien);
    }

    @Override
    public void onNuevoMensaje() {
        Intent i=new Intent(this, DetalleActivity.class);
        startActivity(i);
    }

    @Override
    public void onHistorial() {
        //TODO: intent a la actividad del fragment de historial
    }

    @Override
    public void onAcerdaDe() {
        //TODO: intent a la actividad del fragment de lo que pollas sea esto
    }
}
