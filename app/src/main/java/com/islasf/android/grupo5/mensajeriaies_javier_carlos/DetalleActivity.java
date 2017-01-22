package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DetalleActivity extends AppCompatActivity implements DetalleListeners {


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

    }

    @Override
    public void onSeleccionarDestinatario() {
        //TODO: hacer y abrir fragment de list contactos
    }

    @Override
    public void onSeleccionarRemitente() {
        //TODO: hacer y lanzar fragment de datos del remitente
    }

    @Override
    public void onEnviar() {
        //TODO: enviar mensaje (validando)
    }
}
