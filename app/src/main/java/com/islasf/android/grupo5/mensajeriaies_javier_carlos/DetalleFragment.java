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

/**
 * Este Fragment es el fragment asociado a la pantalla más importante del programa, la del envío de mensajes.
 * Aquí se redireccionarán los listenres de los componentes de dicha pantalla para ser programados
 * en DetalleActivity.
 */
public class DetalleFragment extends Fragment {

    /**
     * Objeto de la interfaz que va a permitir el callback entre Fragment y Activity
     */
    private DetalleListeners listeners;

    /**
     * Método onCreateView que se va a ejecutar cuando la activity cree el Fragment.
     * Éste método busca el layout asociado a este fragment y lo infla.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle, container, false);
    }

    /**
     * Método similar al onCreate en las activities. En él programamos los listeners de todos
     * los componentes con los que el usuario va a interaccionar en esta pantalla. Dentro de cada listener
     * hacemos una llamada al método correspondiente de las rutinas callback.
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Asignamos los tres listeners a los botones
        Button btnDestinatario = (Button) getView().findViewById(R.id.btnDestinatario);
        btnDestinatario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onSeleccionarDestinatario();
            }
        });

        Button btnRemitente = (Button) getView().findViewById(R.id.btnRemitente);
        btnRemitente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onSeleccionarRemitente();
            }
        });

        Button btnEnviarSMS = (Button) getView().findViewById(R.id.btnEnviarSMS);
        btnEnviarSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onEnviarSMS();
            }
        });

        Button btnEnviarEmail = (Button) getView().findViewById(R.id.btnEnviarEmail);
        btnEnviarEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onEnviarEmail();
            }
        });

        RadioButton rbInfo = (RadioButton) getView().findViewById(R.id.rbInfo);
        RadioButton rbUrg = (RadioButton) getView().findViewById(R.id.rbUrgente);

        RadioButton rbDesea = (RadioButton) getView().findViewById(R.id.rbtnDesea);
        RadioButton rbVolvera = (RadioButton) getView().findViewById(R.id.rbtnVolvera);

    }

    /**
     * Setter para el objeto que proporciona los métodos de callback con la Activity
     * @param listeners Objeto que implementa rutinas callback
     */
    public void setListeners(DetalleListeners listeners){
        this.listeners=listeners;
    }

}
