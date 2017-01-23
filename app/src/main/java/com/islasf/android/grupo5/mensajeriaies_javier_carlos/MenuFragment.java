package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MenuFragment extends Fragment {

    private MenuListeners listeners;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout de este fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Al ser un fragment hay que asignarle los listeners por código, no los coge del xml
        Button btnNuevo=(Button) getView().findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nuevoMensajeListener();
            }
        });

        Button btnHistorial=(Button) getView().findViewById(R.id.btnHistorial);
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                historialListener();
            }
        });

        Button btnAcerca=(Button) getView().findViewById(R.id.btnAbout);
        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                acercaDeListener();
            }
        });
    }

    public void setMenuListener(MenuListeners listeners){
        this.listeners=listeners;
    }

    private void nuevoMensajeListener(){
        listeners.onNuevoMensaje();
    }

    public void historialListener(){
        listeners.onHistorial();
    }

    public void acercaDeListener(){
        listeners.onAcerdaDe();
    }
}
