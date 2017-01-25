package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

/**
 * Clase RecogidaContactos.
 * <p>
 * El propósito de esta clase es el recoger todos los contactos de la base de datos propia del sistema.
 * <p>
 * La clase extiende de {@link AsyncTask AsyncTask}, ya que la extracción de contactos de la base de datos
 * es una tarea pesada, por lo que se hace en un hilo secundario.
 * <p>
 * Por parámetros recibe un Content Resolver, que lo necesitaremos para iniciar los cursores encargados de recorrer la bbdd.
 * Además controla mediante un integer el progreso de una progressbar.
 * Devuelve un ArrayList de Contactos con todos los contactos que hay en la base de datos, su nombre, su correo y su email..
 */
public class RecogidaContactos extends AsyncTask<ContentResolver, Integer, ArrayList<Contacto>> {


    private CallbackAsynctask callbackAsynctask;
    private ProgressBar pb;
    int maximo = 1000;

    /**
     * Constructor de la clase, creado para recoger la interfaz del callback y la progressbar.
     *
     * @param callbackAsynctask la interfaz del callback.
     * @param pb                la progressbar a la que se va a ir añadiendo progreso.
     */
    public RecogidaContactos(CallbackAsynctask callbackAsynctask, ProgressBar pb) {
        this.pb = pb;
        this.callbackAsynctask = callbackAsynctask;
    }

    /**
     * Este método se ejecuta antes del doInBackground. Se encarga de hacer visible la ProgressBar y de
     * establecerle un máximo.
     */
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        pb.setVisibility(View.VISIBLE);
        pb.setMax(maximo);
    }

    /**
     * Se ejecuta cuando se llama al método pusblishProgress dentro del método doInBackground. Se encarga de actualizar
     * la ProgressBar, incrementando su progreso.
     *
     * @param values el número en el que se va a incrementar la progressbar.
     */
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        pb.incrementProgressBy(values[0]);
    }

    /**
     * Se ejecuta al terminar el método doInBackground. Se encarga de hacer desaparecer la ProgressBar y de llamar a la interfaz
     * callbackAsynctask, para pasar al hilo principal el conjunto de contactos una vez la recogida de los mismos ha terminado.
     *
     * @param contactos la colección de contactos que devuelve el método doInBackground.
     */
    @Override
    protected void onPostExecute(ArrayList<Contacto> contactos) {
        super.onPostExecute(contactos);
        pb.setVisibility(View.GONE);
        callbackAsynctask.arrayListCargado(contactos);
    }

    /**
     * Este método se encarga de realizar las operaciones en segundo plano, es decir, recoge todos los datos de la bbdd y los va almacenando.
     * Tiene dos cursores para recoger los datos de los contactos, ya que para recoger email y teléfono deben utilizarse dos URIs diferentes.
     *
     * @param params El contentResolver utilizado para iniciar los cursores.
     * @return Un ArrayList con todos los contactos de la base de datos.
     */
    @Override
    protected ArrayList<Contacto> doInBackground(ContentResolver... params) {
        ArrayList<Contacto> listaContactos = new ArrayList<>();
        ContentResolver cr = params[0];

        Cursor curContact = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);


        while (curContact.moveToNext()) {
            int incremento = maximo / curContact.getCount(); //Forzamos la carga de datos.
            String nombre = "";
            String telefono = "";
            String email = "";

            String idContacto = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID));
            nombre = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            telefono = curContact.getString(curContact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            Cursor curEmail = cr.query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = " + idContacto, null, null);

            while (curEmail.moveToNext()) {
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
