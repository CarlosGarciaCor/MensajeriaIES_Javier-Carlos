package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class DetalleFragment extends Fragment {

    private DetalleListeners listeners;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Asignamos los tres listeners a los botones
        Button btnDestinatario = (Button) getView().findViewById(R.id.btnDestinatario);
        btnDestinatario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                destinatarioListener();
            }
        });

        Button btnRemitente = (Button) getView().findViewById(R.id.btnRemitente);
        btnRemitente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                remitenteListener();
            }
        });

        Button btnEnviarSMS = (Button) getView().findViewById(R.id.btnEnviarSMS);
        btnEnviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarSMS();
            }
        });

        Button btnEnviarEmail = (Button) getView().findViewById(R.id.btnEnviarEmail);
        btnEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEmail();
            }
        });

        RadioButton rbInfo = (RadioButton) getView().findViewById(R.id.rbInfo);
        RadioButton rbUrg = (RadioButton) getView().findViewById(R.id.rbUrgente);

        RadioButton rbDesea = (RadioButton) getView().findViewById(R.id.rbtnDesea);
        RadioButton rbVolvera = (RadioButton) getView().findViewById(R.id.rbtnVolvera);

    }

    public void setListeners(DetalleListeners listeners){
        this.listeners=listeners;
    }

    public void destinatarioListener(){
        listeners.onSeleccionarDestinatario();
    }

    public void remitenteListener(){
        listeners.onSeleccionarRemitente();
    }

    public void enviarSMS(){
        listeners.onEnviarSMS();
    }

    public void enviarEmail(){
        listeners.onEnviarEmail();
    }
}
