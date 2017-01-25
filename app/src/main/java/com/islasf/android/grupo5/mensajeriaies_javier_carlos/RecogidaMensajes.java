package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Clase RecogidaMensajes.
 * <p>
 * El propósito de esta clase es el recoger todos los mensajes de la base de datos.
 * <p>
 * La clase extiende de {@link AsyncTask AsyncTask}, ya que la extracción de mensajes de la base de datos
 * es una tarea pesada por lo que se hace en un hilo secundario.
 * <p>
 * Por parámetros recibe una SQLiteDatabase, es decir, una instancia legible de la base de datos de la que sacar los mensajes.
 * Devuelve un ArrayList de Mensajes con todos los mensajes que hay en la base de datos.
 */
public class RecogidaMensajes extends AsyncTask<SQLiteDatabase, Void, ArrayList<Mensaje>> {

    private CallbackAsynctaskM callback;

    /**
     * Constructor de la clase, creado para recoger la interfaz del callback.
     * @param callback la interfaz del callback.
     */
    public RecogidaMensajes(CallbackAsynctaskM callback) {
        this.callback = callback;
    }

    /**
     * <p>Se ejecuta en el hilo principal después del método {@link #doInBackground}. El
     * resultado que le entra es el devuelto por {@link #doInBackground}.</p>
     *
     * @param mensajes Los mensajes devueltos por {@link #doInBackground}.
     * @see #onPreExecute
     * @see #doInBackground
     * @see #onCancelled(Object)
     */
    @Override
    protected void onPostExecute(ArrayList<Mensaje> mensajes) {
        super.onPostExecute(mensajes);
        callback.arrayListCargado(mensajes);
    }

    /**
     * Este método se encarga de realizar las operaciones en segundo plano, es decir, recoge todos los datos de la bbdd y los va almacenando.
     *
     * @param params La instancia legible de la base de datos.
     * @return Un ArrayList de mensajes.
     */
    @Override
    protected ArrayList<Mensaje> doInBackground(SQLiteDatabase... params) {
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        String asunto = "";
        String destinatario = "";
        String remitente = "";
        String cuerpo = "";
        String hora = "";

        SQLiteDatabase db = params[0];
        if (db != null) {
            Cursor c = db.rawQuery("SELECT * FROM mensaje", null);
            if (c.moveToFirst()) {
                while (c.moveToNext()) {
                    // En este primer cursor recojo IDs, y ahora viene la maravilla.
                    destinatario = c.getString(5);
                    remitente = c.getString(6);
                    asunto = c.getString(1);
                    cuerpo = c.getString(2);
                    hora = c.getString(7);

                    Mensaje mensaje = new Mensaje();
                    mensaje.setDestinatario(new Contacto(destinatario));
                    mensaje.setRemitente(new Contacto(remitente));
                    mensaje.setAsunto(asunto);
                    mensaje.setCuerpoMensaje(cuerpo);
                    mensaje.setHora(hora);
                    mensajes.add(mensaje);
                }

            }

            c.close();

            return mensajes;
        } else {
            return null;
        }
    }
}
