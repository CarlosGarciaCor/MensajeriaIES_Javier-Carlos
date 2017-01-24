package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosG on 24/01/2017.
 */

public class MensajesSQLiteHelper extends SQLiteOpenHelper{

    private String sqlmensaje =
            "CREATE TABLE mensaje " +
                    "(_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "asunto TEXT," +
                    "cuerpo TEXT," +
                    "urgente BOOLEAN," +
                    "llamara BOOLEAN," +
                    "destinatario TEXT," +
                    "remitente TEXT," +
                    "fecha DATETIME DEFAULT CURRENT_TIMESTAMP)";


    public MensajesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlmensaje);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mensaje");

        db.execSQL(sqlmensaje);
    }
}
