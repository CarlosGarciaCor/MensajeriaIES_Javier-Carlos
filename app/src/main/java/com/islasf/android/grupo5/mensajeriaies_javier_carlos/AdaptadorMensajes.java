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

    private ArrayList<Mensaje> listado;

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
            holder.cuerpo = (TextView)item.findViewById(R.id.tvCuerpo);
            holder.hora = (TextView)item.findViewById(R.id.tvHora);
            holder.destinatario = (TextView)item.findViewById(R.id.tvDestinatarioH);
            holder.remitente = (TextView)item.findViewById(R.id.tvRemitenteH);
            holder.asunto = (TextView)item.findViewById(R.id.tvAsuntoH);
            item.setTag(holder);
        }
        else{
            holder = (MensajesHolder)item.getTag();
        }

        holder.destinatario.setText(listado.get(position).getDestinatario().getNombre());
        holder.remitente.setText(listado.get(position).getRemitente().getNombre());
        holder.asunto.setText(listado.get(position).getAsunto());
        holder.cuerpo.setText(listado.get(position).getCuerpoMensaje());
        holder.hora.setText(listado.get(position).getHora());

        return item;
    }
}
