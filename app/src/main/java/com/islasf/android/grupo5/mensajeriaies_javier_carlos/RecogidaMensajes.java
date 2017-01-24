package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * Created by CarlosG on 24/01/2017.
 */

public class RecogidaMensajes extends AsyncTask<SQLiteDatabase, Void, ArrayList<Mensaje>>{
    @Override
    protected ArrayList<Mensaje> doInBackground(SQLiteDatabase... params) {
        ArrayList<Mensaje> mensajes = new ArrayList<>();
        String asunto = "";
        String destinatario = "";
        String remitente = "";
        String cuerpo = "";
        String hora = "";

        SQLiteDatabase db = params[0];
        if (db != null){
            Cursor c = db.rawQuery("SELECT * FROM mensaje", null);
            if (c.moveToFirst()){
                while (c.moveToNext()){
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
        }
        else{
            return null;
        }
    }
}
