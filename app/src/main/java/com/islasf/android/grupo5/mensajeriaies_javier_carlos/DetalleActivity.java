package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DetalleActivity extends AppCompatActivity implements DetalleListeners {

    private Mensaje mensaje;

    private RadioButton rbUrgente;
    private RadioButton rbInfo;
    private RadioButton rbtnVolvera;
    private RadioButton rbtnDesea;
    private EditText etAsunto;
    private EditText etMensaje;
    private Button btnDestinatario;
    private Button btnRemitente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        DetalleFragment fragment=(DetalleFragment)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        fragment.setListeners(this);

        //Initializamos las Views de las que se saca la info del mensaje
        rbInfo = (RadioButton) findViewById(R.id.rbInfo);
        rbUrgente = (RadioButton) findViewById(R.id.rbUrgente);
        rbtnDesea = (RadioButton) findViewById(R.id.rbtnDesea);
        rbtnVolvera = (RadioButton) findViewById(R.id.rbtnVolvera);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnDestinatario = (Button) findViewById(R.id.btnDestinatario);
        btnRemitente = (Button) findViewById(R.id.btnRemitente);

        rbtnDesea.setChecked(true);
        rbInfo.setChecked(true);
        mensaje = new Mensaje(this.getApplicationContext());
    }

    @Override
    public void onSeleccionarDestinatario() {
        Intent i=new Intent(this, ContactosActivity.class);
        startActivityForResult(i, 0);
    }

    @Override
    public void onSeleccionarRemitente() {
        Intent i=new Intent(this, RemitenteActivity.class);
        if (mensaje.getRemitente()!=null)
            i.putExtra("Remitente", mensaje.getRemitente());

        startActivityForResult(i, 1);
    }

    @Override
    public void onEnviarSMS() {
        setCampos();
        mensaje.enviarMensajeSMS();
    }

    @Override
    public void onEnviarEmail() {
        setCampos();
        mensaje.enviarMensajeEmail();
    }

    private void setCampos(){
        mensaje.setJustInfo(rbInfo.isChecked());
        mensaje.setUrgent(rbUrgente.isChecked());

        mensaje.setCallMe(rbtnDesea.isChecked());
        mensaje.setIllCall(rbtnVolvera.isChecked());

        mensaje.setAsunto(etAsunto.getText().toString());
        mensaje.setCuerpoMensaje(etMensaje.getText().toString());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Recoger el contacto a la vuelta de la lista y meterlo al Mensaje
        if (requestCode==0 && resultCode==1){
            Contacto destinatario=(Contacto)data.getSerializableExtra("Contacto");
            mensaje.setDestinatario(destinatario);
            btnDestinatario.setText(destinatario.getNombre());
        }

        //recoger remitente y meterlo en Mensaje
        if (requestCode==1 && resultCode==2){
            Contacto remitente = (Contacto)data.getSerializableExtra("Contacto");
            mensaje.setRemitente(remitente);
            btnRemitente.setText(remitente.getNombre());
        }
    }
}
