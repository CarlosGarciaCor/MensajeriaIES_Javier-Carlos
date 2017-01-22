package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class probatinasContactos extends AppCompatActivity {

    ListView listadoContactos;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_probationascontactos);
        listadoContactos = (ListView)findViewById(R.id.lvContactos);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            verContactos();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                verContactos();
            }
        }
    }

    private void verContactos(){
        RecogidaContactos recogida = new RecogidaContactos();
        ArrayList<Contacto> ole = recogida.doInBackground(getContentResolver());

        AdaptadorContactos adaptador = new AdaptadorContactos(this, ole);
        listadoContactos.setAdapter(adaptador);
    }
}
