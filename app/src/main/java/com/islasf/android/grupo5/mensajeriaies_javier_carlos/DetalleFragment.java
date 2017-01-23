package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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

        Button btnEnviar = (Button) getView().findViewById(R.id.btnEnviar);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviar();
            }
        });
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

    public void enviar(){
        listeners.onEnviar();
    }
}
