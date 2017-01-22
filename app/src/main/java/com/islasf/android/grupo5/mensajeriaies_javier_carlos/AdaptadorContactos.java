package com.islasf.android.grupo5.mensajeriaies_javier_carlos;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * TODO
 */
public class AdaptadorContactos extends ArrayAdapter<Contacto>{

    private ArrayList<Contacto> contactos;

    public AdaptadorContactos(Context context, ArrayList<Contacto> contactos) {
        super(context, R.layout.listado_contactos, contactos);
        this.contactos = contactos;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ContactosHolder holder;

        if (item == null){
            LayoutInflater inflador = LayoutInflater.from(getContext());
            item = inflador.inflate(R.layout.listado_contactos, null);

            holder = new ContactosHolder();
            holder.nombre = (TextView)item.findViewById(R.id.tvNombre);
            holder.numero = (TextView)item.findViewById(R.id.tvTelefono);
            holder.email = (TextView)item.findViewById(R.id.tvEmail);
            item.setTag(holder);
        }
        else{
            holder = (ContactosHolder)item.getTag();
        }

        holder.nombre.setText(contactos.get(position).getNombre());
        holder.numero.setText(contactos.get(position).getTelefono());
        holder.email.setText(contactos.get(position).getEmail());

        return item;
    }
}
