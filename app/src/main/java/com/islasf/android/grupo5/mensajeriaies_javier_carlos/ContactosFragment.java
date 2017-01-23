package com.islasf.android.grupo5.mensajeriaies_javier_carlos;


import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContactosFragment extends Fragment {

    private ListView listadoContactos;
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    private ContactosListeners listeners;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactos, container, false);
    }

    public void setListeners(ContactosListeners listeners){
        this.listeners=listeners;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        listadoContactos = (ListView) getView().findViewById(R.id.lvContactos);
        listadoContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Recogemos los datos del contacto del item seleccionado y se los pasamos a la actividad
                String nombre = ((TextView) view.findViewById(R.id.tvNombre)).getText().toString();
                String telefono = ((TextView) view.findViewById(R.id.tvTelefono)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.tvEmail)).getText().toString();

                listeners.onSelectContacto(new Contacto(nombre, email, telefono));
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            verContactos();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                verContactos();
            }
        }
    }

    private void verContactos(){
        RecogidaContactos recogida = new RecogidaContactos();
        ArrayList<Contacto> ole = recogida.doInBackground(getContext().getContentResolver());

        AdaptadorContactos adaptador = new AdaptadorContactos(getContext(), ole);
        listadoContactos.setAdapter(adaptador);
    }

}
