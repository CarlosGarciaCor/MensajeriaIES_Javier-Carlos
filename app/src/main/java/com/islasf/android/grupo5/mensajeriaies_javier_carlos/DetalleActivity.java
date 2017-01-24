package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

        mensaje = new Mensaje();

        inicializarComponentes();
    }

    private void inicializarComponentes(){
        //Initializamos las Views de las que se saca la info del mensaje
        rbInfo = (RadioButton) findViewById(R.id.rbInfo);
        rbUrgente = (RadioButton) findViewById(R.id.rbUrgente);
        rbtnDesea = (RadioButton) findViewById(R.id.rbtnDesea);
        rbtnVolvera = (RadioButton) findViewById(R.id.rbtnVolvera);
        etAsunto = (EditText) findViewById(R.id.etAsunto);
        etMensaje = (EditText) findViewById(R.id.etMensaje);
        btnDestinatario = (Button) findViewById(R.id.btnDestinatario);
        btnRemitente = (Button) findViewById(R.id.btnRemitente);

        RadioGroup g1 = (RadioGroup) findViewById(R.id.rgOpciones1);
        g1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbtnDesea:
                        rbtnVolvera.setChecked(false);
                        mensaje.deseaQueLoLlamen();
                        break;
                    case R.id.rbtnVolvera:
                        rbtnDesea.setChecked(false);
                        mensaje.volveraALlamar();
                        break;
                }

                mensaje.modelarAsunto();
                etAsunto.setText(mensaje.getAsunto());
            }
        });

        RadioGroup g2 = (RadioGroup) findViewById(R.id.rgOpciones2);
        g2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rbInfo:
                        rbUrgente.setChecked(false);
                        mensaje.isJustInfo();
                        break;
                    case R.id.rbUrgente:
                        rbInfo.setChecked(false);
                        mensaje.esUrgente();
                        break;
                }

                mensaje.modelarAsunto();
                etAsunto.setText(mensaje.getAsunto());
            }
        });

        rbtnDesea.setChecked(true);
        rbInfo.setChecked(true);
        mensaje.modelarAsunto();
        etAsunto.setText(mensaje.getAsunto());
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

        if (mensaje.validarMensajeSMS()){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + mensaje.getDestinatario().getTelefono()));
            i.putExtra("sms_body", mensaje.getAsunto() + "\n[Mensaje]: " +mensaje.getCuerpoMensaje());
            startActivity(Intent.createChooser(i, "Enviar sms..."));
            guardarEnBBDD();
        }

        else
            Toast.makeText(this, "No pudo enviar el SMS porque faltan campos por rellenar", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onEnviarEmail() {
        setCampos();
        if (mensaje.validarMensajeEmail()){
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("message/rfc822");
            i.putExtra(Intent.EXTRA_EMAIL, new String[]{mensaje.getDestinatario().getEmail()});
            i.putExtra(Intent.EXTRA_SUBJECT, mensaje.getAsunto());
            i.putExtra(Intent.EXTRA_TEXT, mensaje.getCuerpoMensaje());
            startActivity(Intent.createChooser(i, "Enviar correo..."));

            guardarEnBBDD();
        }
        else{
            Toast.makeText(this, "No pudo enviar el correo porque faltan campos por rellenar", Toast.LENGTH_SHORT).show();
        }
    }

    private void setCampos(){
        if (rbtnDesea.isChecked())
            mensaje.deseaQueLoLlamen();
        else
            mensaje.volveraALlamar();

        mensaje.setAsunto(etAsunto.getText().toString());
        mensaje.setCuerpoMensaje(etMensaje.getText().toString());
    }

    private void guardarEnBBDD(){
        MensajesSQLiteHelper dbhelper = new MensajesSQLiteHelper(this, "Mensajeitor3000", null, 1);
        SQLiteDatabase db = dbhelper.getWritableDatabase();

        if (db != null){
            db.execSQL("INSERT OR IGNORE INTO mensaje(_id, asunto, cuerpo, urgente, llamara, destinatario, remitente) VALUES (null, '"
                    +mensaje.getAsunto()+"', '"+mensaje.getCuerpoMensaje()+"', '"+mensaje.isUrgent()+"', '"+mensaje.isVolveraALlamar()+"', '"
                    +mensaje.getDestinatario().getNombre()+"', '"+mensaje.getRemitente().getNombre()+"')");

            db.close();
        }
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
            mensaje.modelarAsunto();
            btnRemitente.setText(remitente.getNombre());
            etAsunto.setText(mensaje.getAsunto());
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mensaje.setCuerpoMensaje(etMensaje.getText().toString());
        outState.putSerializable("Mensaje", mensaje);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        mensaje=(Mensaje)savedInstanceState.getSerializable("Mensaje");

        if (mensaje.getDestinatario()!=null)
            btnDestinatario.setText(mensaje.getDestinatario().getNombre());
        if (mensaje.getRemitente()!=null)
            btnRemitente.setText(mensaje.getRemitente().getNombre());

        if (mensaje.isVolveraALlamar())
            rbtnVolvera.setChecked(true);
        else
            rbtnDesea.setChecked(true);

        if (mensaje.isUrgent())
            rbUrgente.setChecked(true);
        else
            rbInfo.setChecked(true);

        mensaje.modelarAsunto();
        etAsunto.setText(mensaje.getAsunto());

        etMensaje.setText(mensaje.getCuerpoMensaje());
    }
}
