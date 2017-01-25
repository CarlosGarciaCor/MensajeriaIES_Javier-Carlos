package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Actividad en la cual el usuario va a poder introducir los datos de un remitente
 * para poder pasarselo a DetalleActivity e ir formando el mensaje
 */
public class RemitenteActivity extends AppCompatActivity implements RemitenteListeners {

    /** Campo de texto del teléfono*/
    private EditText etTelefono;
    /** Campo de texto del email*/
    private EditText etEmail;
    /** Campo de texto del nombre de contacto*/
    private EditText etPersona;
    /** Campo de texto de la empresa del contacto*/
    private EditText etEmpresa;

    /**
     * En el onCreate iniciamos todos los campos de texto y en caso de tener
     * un objeto Contacto ya guardado en el bundle les asignamos los datos
     * de este contacto. Esto pasará cuando hayamos elegido un contacto previamente
     * y volvamos a esta actividad para editarlo.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remitente);

        RemitenteFragment fragment = (RemitenteFragment) getSupportFragmentManager().findFragmentById(R.id.FrgRemitente);
        fragment.setListeners(this);

        etTelefono = (EditText) findViewById(R.id.etTelefono);
        etEmail = (EditText) findViewById(R.id.etEmail);
        etPersona = (EditText) findViewById(R.id.etPersona);
        etEmpresa = (EditText) findViewById(R.id.etEmpresa);

        Contacto contacto=(Contacto)getIntent().getSerializableExtra("Remitente");

        if (contacto!=null){
            etTelefono.setText(contacto.getTelefono());
            etEmail.setText(contacto.getEmail());
            etPersona.setText(contacto.getNombre());
            etEmpresa.setText(contacto.getEmpresa());
        }
    }

    /**
     * Este método se va a ejecutar cuando se pulse el botón Aceptar del fragment. En el se va a
     * montar el contacto con la información de los campos de texto, después se va a
     * validar el contacto (usando la clase Contacto, lanzando un Toast si no es válido) y por
     * último vamos a guardar dicho contacto en un Intent para poder abrirlo desde
     * la activity que ha lanzado a esta.
     */
    @Override
    public void onAceptar() {
        Contacto contacto = new Contacto(etPersona.getText().toString(),
                etEmpresa.getText().toString(),
                etEmail.getText().toString(),
                etTelefono.getText().toString());
        if (contacto.isValid()){
            Intent data= new Intent();
            data.putExtra("Contacto", contacto);
            setResult(2, data);
            finish();
        }

        else{
            Toast t=Toast.makeText(this, "El remitente no es válido", Toast.LENGTH_SHORT);
            t.show();
        }
    }

}
