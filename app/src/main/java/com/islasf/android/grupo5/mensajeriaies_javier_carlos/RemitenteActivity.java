package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class RemitenteActivity extends AppCompatActivity implements RemitenteListeners {

    private EditText etTelefono;
    private EditText etEmail;
    private EditText etPersona;
    private EditText etEmpresa;

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
