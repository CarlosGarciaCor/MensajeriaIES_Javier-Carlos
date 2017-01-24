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
 * Created by CarlosG on 24/01/2017.
 */

public class AdaptadorMensajes extends ArrayAdapter<Mensaje>{

    ArrayList<Mensaje> listado;

    public AdaptadorMensajes(Context context, ArrayList<Mensaje> objects) {
        super(context, R.layout.listado_mensajes, objects);
        this.listado = objects;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        MensajesHolder holder;

        if (item == null){
            LayoutInflater inflador = LayoutInflater.from(getContext());
            item = inflador.inflate(R.layout.listado_mensajes, null);

            holder = new MensajesHolder();
            holder.destinatario = (TextView)item.findViewById(R.id.tvDestinatario);
            holder.remitente = (TextView)item.findViewById(R.id.tvRemitente);
            holder.asunto = (TextView)item.findViewById(R.id.tvAsunto);
            item.setTag(holder);
        }
        else{
            holder = (MensajesHolder)item.getTag();
        }

        holder.destinatario.setText(listado.get(position).getDestinatario().getNombre());
        holder.remitente.setText(listado.get(position).getRemitente().getNombre());
        holder.asunto.setText(listado.get(position).getAsunto());

        return item;
    }
}
