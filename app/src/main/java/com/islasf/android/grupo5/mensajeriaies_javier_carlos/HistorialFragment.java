package com.islasf.android.grupo5.mensajeriaies_javier_carlos;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class HistorialFragment extends Fragment implements CallbackAsynctaskM{

    private ListView listadoMensajes;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_historial, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoMensajes = (ListView) getView().findViewById(R.id.lvHistorial);

        RecogidaMensajes recogida = new RecogidaMensajes(this);
        MensajesSQLiteHelper helper = new MensajesSQLiteHelper(getContext(), "Mensajeitor3000", null, 1);
        recogida.execute(helper.getReadableDatabase());

    }

    /**
     * Método que se implementará en la clase {@link HistorialFragment HistorialFragment}. En esa clase
     * se recogerá el ArrayList de Mensajes y se pasará al adaptador.
     * <p>
     * Por otro lado, a este método le llama la clase {@link RecogidaMensajes RecogidaMensajes} para pasarle
     * el ArrayList cuando su método doInBackground termine.
     *
     * @param mensajes la colección de mensajes
     */
    @Override
    public void arrayListCargado(ArrayList<Mensaje> mensajes) {
        if (mensajes==null){
            Toast.makeText(getContext(), "No se pudo conectar con la base de datos, inténtelo de nuevo", Toast.LENGTH_SHORT).show();
        }
        else if (mensajes.size()==0){
            Toast.makeText(getContext(), "El historial está vacío", Toast.LENGTH_LONG).show();
        }
        else {
            AdaptadorMensajes adaptador = new AdaptadorMensajes(getContext(), mensajes);
            listadoMensajes.setAdapter(adaptador);
        }
    }
}
