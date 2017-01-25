package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class DetalleActivity extends AppCompatActivity implements DetalleListeners {

    /** Objeto Mensaje que va a ser el que, según el usuario vaya introduciendo
     * datos y cambiando campos, va a evolucionar hasta formar un Mensaje válido para
     * ser enviado.*/
    private Mensaje mensaje;
    /** Radio Button para decir que el mensaje es urgente*/
    private RadioButton rbUrgente;
    /** Radio Button para decir que el mensaje es solo información*/
    private RadioButton rbInfo;
    /** Radio Button para indicar que el remitente volverá a llamar*/
    private RadioButton rbtnVolvera;
    /** /** Radio Button para indicar que el remitente desea que le llamen*/
    private RadioButton rbtnDesea;
    /** Campo de texto del asunto*/
    private EditText etAsunto;
    /** Campo de texto del cuepro del mensaje*/
    private EditText etMensaje;
    /** Botón para seleccionar destinatario*/
    private Button btnDestinatario;
    /** Botón para seleccionar remitente*/
    private Button btnRemitente;
    /** CheckBox para seleccionar la opción de automatizado.*/
    private CheckBox cbAuto;

    /**
     * En el onCreate inicializamos el Mensaje y todos los componentes con los que el usuario interactua.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        DetalleFragment fragment=(DetalleFragment)getSupportFragmentManager().findFragmentById(R.id.FrgDetalle);
        fragment.setListeners(this);

        mensaje = new Mensaje();

        inicializarComponentes();
    }

    /**
     * Este método se llama desde el onCreate e inicializa todos los componentes.
     * Aquí están programados los listeners para los RadioButtons y el CheckBox.
     * Lo que se hace en los Radio Buttons básicamente es desactivar el botón contrario
     * y formar el auto-mensaje en consecuencia (si está habilitado).
     *
     * En el listener del CheckBox limpiamos los campos de texto al desactivar
     * el automatizar.
     */
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
        cbAuto = (CheckBox) findViewById(R.id.cbAuto);

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

                mensaje.modeladoAutomatico();
                etAsunto.setText(mensaje.getAsunto());
                etMensaje.setText(mensaje.getCuerpoMensaje());
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

                if (cbAuto.isChecked()){
                    mensaje.modeladoAutomatico();
                    etAsunto.setText(mensaje.getAsunto());
                    etMensaje.setText(mensaje.getCuerpoMensaje());
                }
            }
        });

        cbAuto.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    mensaje.modeladoAutomatico();
                    etAsunto.setEnabled(false);
                    etMensaje.setEnabled(false);
                    etAsunto.setText(mensaje.getAsunto());
                    etMensaje.setText(mensaje.getCuerpoMensaje());
                }
                else {
                    etAsunto.setEnabled(true);
                    etMensaje.setEnabled(true);
                    etAsunto.setText("");
                    etMensaje.setText("");
                }

            }
        });

        cbAuto.setChecked(true);
        rbtnDesea.setChecked(true);
        rbInfo.setChecked(true);
        mensaje.modeladoAutomatico();
        etAsunto.setText(mensaje.getAsunto());
        etMensaje.setText(mensaje.getCuerpoMensaje());
    }

    /**
     * Al pulsar el botón Seleccionar Destinatario hacemos un Intent a la actividad de los contactos,
     * donde seleccionaremos el destinatario.
     */
    @Override
    public void onSeleccionarDestinatario() {
        Intent i=new Intent(this, ContactosActivity.class);
        startActivityForResult(i, 0);
    }

    /**
     * Al pulsar en Seleccionar Remitente lanzamos un Intent a la actividad de datos del remitente.
     * Si el remitente que tenemos en el contacto existe, lo metemos en el Intent
     * para que la otra actividad se cree con los datos ya elegidos.
     */
    @Override
    public void onSeleccionarRemitente() {
        Intent i=new Intent(this, RemitenteActivity.class);
        if (mensaje.getRemitente()!=null)
            i.putExtra("Remitente", mensaje.getRemitente());

        startActivityForResult(i, 1);
    }

    /**
     * Este método se ejecuta al pulsar en EnviarSMS.
     *
     * Lo primero que hacemos aquí es validar el mensaje. Si no es válido lanzamos un Toast informado de ello.
     * Si sí es válido vamos a mandarlo. Para ello usamos la clase SMSManager.
     * Como queremos también saber si el mensaje se ha enviado o no, vamos a registrar
     * un broadcast receiver que va a escuchar si se ha enviado dicho mensaje. Este receiver estará relacionado
     * con el PendingIntent que le hemos pasado como parámetro al enviar el mensaje con sms.sendTextMessage(...)
     *
     * Es necesario incluir la línea de permisos correspondiente en el AndroidManifest.xml
     */
    @Override
    public void onEnviarSMS() {
        setCampos();

        if (mensaje.validarMensajeSMS()){

            String SENT="SMS_SENT";
            String text=mensaje.modelarSMS(); //Mensaje formado para SMS (sin asunto)

            PendingIntent enviadoPI = PendingIntent.getBroadcast(this, 0, new Intent(
                    SENT), 0);

            //Escuchamos el envío de SMS con un broadcast receiver local
            registerReceiver(new BroadcastReceiver() {
                @Override
                public void onReceive(Context arg0, Intent arg1) {

                    if (getResultCode()==Activity.RESULT_OK){
                        guardarEnBBDD();
                        Toast.makeText(getBaseContext(), "SMS enviado", Toast.LENGTH_SHORT).show();
                    }
                    else
                        Toast.makeText(getBaseContext(), "No se pudo enviar el SMS", Toast.LENGTH_SHORT).show();
                }
            }, new IntentFilter("SMS_SENT"));


            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(mensaje.getDestinatario().getTelefono(), null, text, enviadoPI, null);
            //El primer PI es para recibir del servidor de mensajería que el SMS ha sido enviado y el segundo (null)
            //para ver si ha sido entregado, si ha llegado a su destinatario.
        }

        else
            Toast.makeText(this, "No pudo enviar el SMS (revise los campos)", Toast.LENGTH_SHORT).show();

    }

    /**
     * Al enviar un email lo primero se valida que el mensaje sea válido, informando al usuario si no lo es con un Toast.
     * Una vez validado, la forma de mandar el email va a ser mediante un Intent implícito, es decir, vamos a lanzar un
     * Intent con una acción definida y podremos abrir con él toda aplicacción del móvil que tenga registrada esa acción.
     *
     * El problema que tenemos aquí es que al ser un Intent explícito nosotros dependemos de la aplicación que se
     * lance para saber si el usuario ha enviado o no el mensaje. Lamentablemente, la aplicación de GMail de Google (la usada
     * para las pruebas) no contempla esta situación y siempre devuelve el mismo código de resultado, se mande el mail o no.
     */
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
            Toast.makeText(this, "No pudo enviar el correo (revise los campos)", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Método privado para formar los campos de texto y los booleanos
     * del mensaje en función de lo que valgan los componentes.
     */
    private void setCampos(){
        if (rbtnDesea.isChecked())
            mensaje.deseaQueLoLlamen();
        else
            mensaje.volveraALlamar();

        mensaje.setAsunto(etAsunto.getText().toString());
        mensaje.setCuerpoMensaje(etMensaje.getText().toString());
    }

    /**
     * Método para guardar un mensaje en la base de datos. Una vez lanzado el mensaje, se llamará
     * a este método donde se hará el insert en nuestra base de datos usando las clases
     * MensajesSQLLiteHelper y SQLiteDatabase.
     */
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

    /**
     * El onActivityResult se ejecuta al volver de otra actividad. Esto en esta clase
     * sucede dos veces:
     *
     * 1. Al seleccionar el destinatario:
     * Simplemente recogemos el contacto guardado en el Intent data y lo guardamos
     * en el Mensaje.
     *
     * 2. Al seleccionar el remitente
     * Lo mismo pero con el remitente, incluyendo además el modelado del mensaje
     * si la opción de automatizado está activada.
     *
     */
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

            if (cbAuto.isChecked()){
                mensaje.modeladoAutomatico();
                etAsunto.setText(mensaje.getAsunto());
                etMensaje.setText(mensaje.getCuerpoMensaje());
            }

            btnRemitente.setText(remitente.getNombre());
        }
    }

    /**
     * Guardamos el estado al rotar la pantalla. Lo único que interesa guardar es el objeto
     * Mensaje, que implementa Serializable para esta ocasión
     * @param outState
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        mensaje.setCuerpoMensaje(etMensaje.getText().toString());
        outState.putSerializable("Mensaje", mensaje);
    }

    /**
     * Al rehacer la pantalla al rotar, tenemos que volver a construir todos los campos
     * en función de los valores de los atributos del Mensaje. Es lo que se hace en este método.
     * @param savedInstanceState
     */
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

        if (cbAuto.isChecked()){
            mensaje.modeladoAutomatico();
        }

        etAsunto.setText(mensaje.getAsunto());
        etMensaje.setText(mensaje.getCuerpoMensaje());
    }
}
