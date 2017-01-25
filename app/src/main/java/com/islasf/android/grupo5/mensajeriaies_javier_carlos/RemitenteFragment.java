package com.islasf.android.grupo5.mensajeriaies_javier_carlos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragmento asociado a la pantalla de carga de datos del remitente.
 */
public class RemitenteFragment extends Fragment {

    /**
     * Objeto de la interfaz que va a permitir el callback entre Fragment y Activity
     */
    private RemitenteListeners listeners;

    /**
     * Método onCreateView que se va a ejecutar cuando la activity cree el Fragment.
     * Éste método busca el layout asociado a este fragment y lo infla.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remitente, container, false);
    }

    /**
     * Método similar al onCreate de las activities. En él vamos a instanciar el botón de Aceptar
     * y meterle un listener que llamará a la función callback en RemitenteActivity
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button btnAceptar = (Button) getView().findViewById(R.id.btnAceptar);
        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onAceptar();
            }
        });
    }

    /**
     * Setter para el objeto que proporciona los métodos de callback con la Activity
     * @param listeners Objeto que implementa rutinas callback
     */
    public void setListeners(RemitenteListeners listeners){
        this.listeners=listeners;
    }
}
