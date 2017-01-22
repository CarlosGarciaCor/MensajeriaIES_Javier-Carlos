package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;

import java.util.ArrayList;

/**
 * TODO
 */
public class RecogidaContactos extends AsyncTask<ContentResolver, Void, ArrayList<Contacto>>{

    @Override
    protected ArrayList<Contacto> doInBackground(ContentResolver... params) {
        /*
            TODO: Recoger contactos.
            La idea es:
                1. Con un primer cursor recorro todos los contactos. Recojo su ID y su nombre.
                2. A su vez, dentro de este cursor corren paralelamente otros dos cursores, se encargan del mail y el teléfono.
                3. Estos dos últimos cursores van recogiendo la selección comparando por cursor. Es decir, en el cursor hay que poner como condición que _ID = _ID.
         */

        ArrayList<Contacto> listaContactos = new ArrayList<>();
        ContentResolver cr = params[0];

        Cursor curContact = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);

        while (curContact.moveToNext()){
            String nombre = "";
            String telefono = "";
            String email = "";

            String idContacto = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            nombre = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            telefono = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Cursor curEmail = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + idContacto, null, null);

            while (curEmail.moveToNext()){
                email = curEmail.getString(curEmail.getColumnIndex(ContactsContract.CommonDataKinds.Email.ADDRESS));
            }

            curEmail.close();

            Contacto c1 = new Contacto(email, telefono, nombre);
            listaContactos.add(c1);
        }

        curContact.close();

        return listaContactos;
    }
}
