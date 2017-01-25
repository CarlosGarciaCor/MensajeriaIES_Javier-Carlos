package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * TODO
 */
public class RecogidaContactos extends AsyncTask<ContentResolver, Integer, ArrayList<Contacto>>{


    private CallbackAsynctask callbackAsynctask;
    private ProgressBar pb;
    int maximo = 1000;

    public RecogidaContactos(CallbackAsynctask callbackAsynctask, ProgressBar pb) {
        this.pb = pb;
        this.callbackAsynctask = callbackAsynctask;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pb.setVisibility(View.VISIBLE);
        pb.setMax(maximo);
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        pb.incrementProgressBy(values[0]);
    }

    @Override
    protected void onPostExecute(ArrayList<Contacto> contactos) {
        super.onPostExecute(contactos);
        pb.setVisibility(View.GONE);
        callbackAsynctask.arrayListCargado(contactos);
    }

    @Override
    protected ArrayList<Contacto> doInBackground(ContentResolver... params) {
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        ContentResolver cr = params[0];

        Cursor curContact = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


        while (curContact.moveToNext()){
            int incremento = maximo / curContact.getCount(); //Forzamos la carga de datos.
            String nombre = "";
            String telefono = "";
            String email = "";

            String idContacto = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            nombre = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            telefono = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Cursor curEmail = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + idContacto, null, null);

            while (curEmail.moveToNext()){
                curEmail.getCount();
                email = curEmail.getString(curEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            }

            curEmail.close();

            Contacto c1 = new Contacto(nombre, null, email, telefono);
            listaContactos.add(c1);

            publishProgress(incremento);

        }

        curContact.close();

        return listaContactos;
    }

}
