package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DetalleActivity extends AppCompatActivity implements DetalleListeners {

    private Mensaje mensaje;

    private RadioGroup rgOpciones;
    private CheckBox cbUrgente;
    private CheckBox cbInfo;
    private RadioButton rbtnVolvera;
    private RadioButton rbtnDesea;
    private EditText etAsunto;
    private EditText etMensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        DetalleFragment fragment=(DetalleFragment)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        fragment.setListeners(this);

        //Initializamos las Views de las que se saca la info del mensaje
        rgOpciones = (RadioGroup) findViewById(R.id.rgOpciones);
        cbInfo = (CheckBox) findViewById(R.id.cbInfo);
        cbUrgente = (CheckBox) findViewById(R.id.cbUrgente);
        rbtnDesea = (RadioButton) findViewById(R.id.rbtnDesea);
        rbtnVolvera = (RadioButton) findViewById(R.id.rbtnVolvera);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etMensaje = (EditText) findViewById(R.id.etMensaje);

        //Inicilizamos el mensaje
        Mensaje mensaje = new Mensaje();

    }

    @Override
    public void onSeleccionarDestinatario() {
        Intent i=new Intent(this, ContactosActivity.class);
        startActivityForResult(i, 0);
    }

    @Override
    public void onSeleccionarRemitente() {
        //TODO: hacer y lanzar fragment de datos del remitente
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Recoger el contacto a la vuelta de la lista y meterlo al Mensaje
        if (resultCode==1){
            Log.i("Contacto seleccionado", data.getStringExtra("Contacto"));
        }

        //TODO: resultCode para remitente = 1; recoger remitente y meterlo en Mensaje en otro if
    }

    @Override
    public void onEnviar() {
        //TODO: enviar mensaje
        /*

        ESTO ENVIA MENSAJES DE GMAIL:

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"sanchez.j.gomez@gmail.com", "fernandovado95@gmail.com", "carlos.ga.corpas@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "¡POR FIN HE INVENTADO ALGO QUE FUNCIONA!");
        i.putExtra(Intent.EXTRA_TEXT, "JAJAJAJAJAJA. SIEMPRE GANO. PUTTTTTTOOOOOOOS.");
        startActivity(Intent.createChooser(i, "Enviar correo..."));
        */


        /*

        ESTO ENVIA MENSAJES DE SMS. TE DEJA ELEGIR LA APP COMO EL DE MAIL.
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + 691762950));
        intent.putExtra("sms_body", "pollas");
        startActivity(intent);
        */
    }
}
