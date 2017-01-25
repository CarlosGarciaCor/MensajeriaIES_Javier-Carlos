package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Clase que extiende de {@link SQLiteOpenHelper}. Se utiliza para la gestión de
 * la creación de la base de datos y su actualización, en caso de que la versión de la misma cambie.
 */
public class MensajesSQLiteHelper extends SQLiteOpenHelper {

    /**
     * La sentencia SQL utilizada para la creación de la tabla mensaje.
     */
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


    /**
     * Constructor de la clase. Llama al constructor de la clase heredada.
     *
     * @param context el contexto de la actividad.
     * @param name    el nombre de la bbdd.
     * @param factory una interfaz que permite devolver subclases de la clase Cursor cuando se hacen las consultas.
     * @param version la versión de la bbdd.
     */
    public MensajesSQLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    /**
     * Método al que se llama en la creación de la BBDD. Recibe por parámetro la base de datos y ejecuta la sentencia de creación de la tabla mensaje.
     *
     * @param db la base de datos.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlmensaje);
    }

    /**
     * Método al que se llama en la actualización del formato de la BBDD. Recibe por parámetro la base
     * de datos y ejecuta la sentencia de eliminación de tablas, para luego ejecutar la de creación de la tabla mensaje.
     * <p>
     * La idea sería programar una migración de datos pero no tenemos el conocimiento como para hacerlo.
     *
     * @param db la base de datos.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mensaje");

        db.execSQL(sqlmensaje);
    }
}
