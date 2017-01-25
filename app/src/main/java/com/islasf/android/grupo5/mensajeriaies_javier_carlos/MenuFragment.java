package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Fragment relacionado a la pantalla o menú principal de la aplicación. En los dispositivos móviles
 * el layout asociado a este fragment ocupará toda la pantalla y será cargado al lanzar la aplicación.
 *
 * Aquí se establecen los listeners de los botones del menú y se llama en ellos a la actividad principal
 * mediante rutinas callback.
 */
public class MenuFragment extends Fragment {

    /**
     * Objeto de la interfaz que va a permitir el callback entre Fragment y Activity
     */
    private MenuListeners listeners;

    /**
     * Método onCreateView que se va a ejecutar cuando la activity cree el Fragment.
     * Éste método busca el layout asociado a este fragment y lo infla.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflar el layout de este fragment
        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    /**
     * Método similar al onCreate en las activities en el cual vamos a definir los listeners
     * de los botones del menú y relacionarlos con la actividad principal.
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        //Al ser un fragment hay que asignarle los listeners por código, no los coge del xml
        Button btnNuevo=(Button) getView().findViewById(R.id.btnNuevo);
        btnNuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onNuevoMensaje();
            }
        });

        Button btnHistorial=(Button) getView().findViewById(R.id.btnHistorial);
        btnHistorial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onHistorial();
            }
        });

        Button btnAcerca=(Button) getView().findViewById(R.id.btnAbout);
        btnAcerca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onAcerdaDe();
            }
        });
    }

    /**
     * Setter para el objeto que proporciona los métodos de callback con la Activity
     * @param listeners Objeto que implementa rutinas callback
     */
    public void setMenuListener(MenuListeners listeners){
        this.listeners=listeners;
    }

}
