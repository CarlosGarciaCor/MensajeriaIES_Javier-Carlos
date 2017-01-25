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
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Fragment asociado a la pantalla del listado de contactos. Estos se van a extraer
 * de la base de datos de contactos del móvil.
 */
public class ContactosFragment extends Fragment implements CallbackAsynctask{

    /**
     * Vista del listado de contactos
     */
    private ListView listadoContactos;
    /**
     * El código de los permisos. Puede ser cualquier número mayor que 0
     */
    private static final int PERMISSIONS_REQUEST_READ_CONTACTS = 100;
    /**
     * Objeto de la interfaz que va a permitir el callback entre Fragment y Activity
     */
    private ContactosListeners listeners;
    /**
     * La barra de progreso a la hora de cargar los contactos.
     */
    private ProgressBar progressBar;

    /**
     * Método onCreateView que se va a ejecutar cuando la activity cree el Fragment.
     * Éste método busca el layout asociado a este fragment y lo infla.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contactos, container, false);
    }

    /**
     * Setter para el objeto que proporciona los métodos de callback con la Activity
     * @param listeners Objeto que implementa rutinas callback
     */
    public void setListeners(ContactosListeners listeners){
        this.listeners=listeners;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        progressBar = (ProgressBar) getView().findViewById(R.id.progressBar);

        listadoContactos = (ListView) getView().findViewById(R.id.lvContactos);
        listadoContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Recogemos los datos del contacto del item seleccionado y se los pasamos a la actividad
                String nombre = ((TextView) view.findViewById(R.id.tvNombre)).getText().toString();
                String telefono = ((TextView) view.findViewById(R.id.tvTelefono)).getText().toString();
                String email = ((TextView) view.findViewById(R.id.tvEmail)).getText().toString();

                listeners.onSelectContacto(new Contacto(nombre, null, email, telefono));
            }
        });

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && getContext().checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS}, PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else {
            verContactos();
        }
    }

    /**
     * Método callback a la petición de permisos en Android en tiempo de ejecución. Se llama después de que el usuario decida si
     * conceder o no permiso para leer los contactos a la aplicación.
     * @param requestCode el código con el que lanzamos la petición de permisos.
     * @param permissions los permisos que se le han pedido al usuario.
     * @param grantResults el resultaod de la peticion, puede ser permission_granted o permission_denied.
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST_READ_CONTACTS){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                verContactos();
            }
        }
    }

    /**
     * Inicializa la clase {@link RecogidaContactos}, pasándole al constructor esta clase en sí para poder hacer
     * el callback y la progressbar, para que {@link RecogidaContactos} pueda ir actualizándola.
     */
    private void verContactos(){
        RecogidaContactos recogida = new RecogidaContactos(this, progressBar);
        recogida.execute(getContext().getContentResolver());
    }


    /**
     * Método sobrescrito de la interfaz del callback. En el recogemos la lista de contactos, los metemos al adaptador
     * y le pasamos al listview estos contactos.
     * @param contactos la colección de contactos
     */
    @Override
    public void arrayListCargado(ArrayList<Contacto> contactos) {
        AdaptadorContactos adaptador = new AdaptadorContactos(getContext(), contactos);
        listadoContactos.setAdapter(adaptador);
    }
}
