package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by CarlosG on 24/01/2017.
 */

public class MensajesSQLiteHelper extends SQLiteOpenHelper{

    String sqlcreacion = ""; //TODO Cuando confirmemos estructura.
    String sqldrop = "";

    public MensajesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlcreacion);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqldrop);

        db.execSQL(sqlcreacion);
    }
}