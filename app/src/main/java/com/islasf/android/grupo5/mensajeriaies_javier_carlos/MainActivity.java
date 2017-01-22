package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //TODO Como dejes esto así te pego un tiro Carlos
    public void diohmiowil (View v){
        Intent sifunsionabien = new Intent(this, Contactos.class);
        startActivity(sifunsionabien);
    }
}
