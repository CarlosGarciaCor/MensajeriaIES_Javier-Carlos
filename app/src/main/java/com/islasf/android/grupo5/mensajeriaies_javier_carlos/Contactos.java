package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class Contactos extends AppCompatActivity {

    TextView resultados;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactos);
        resultados = (TextView)findViewById(R.id.txtResultados);
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
        String[] contactos = new String[]{
                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Email.ADDRESS
        };

        Uri contactosUri = ContactsContract.Contacts.CONTENT_URI;
        ContentResolver cr = getContentResolver();

        // TODO Aqui lanza exception, es lo que estoy mirando
        Cursor cursor = cr.query(contactosUri, contactos, null, null, null);

        if (cursor.moveToFirst()) {
            String nombre;
            String numero;
            String correo;

            int indiceColNombre = cursor.getColumnIndex(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME);
            int indiceColNumero = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
            int indiceColCorreo = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS);

            resultados.setText("");

            do {
                nombre = cursor.getString(indiceColNombre);
                numero = cursor.getString(indiceColNumero);
                correo = cursor.getString(indiceColCorreo);

                resultados.append(nombre + " - " + numero + " - " + correo);
            } while (cursor.moveToNext());
        }
    }

    public void pillarContactos(View v){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            verContactos();
        }
    }
}
