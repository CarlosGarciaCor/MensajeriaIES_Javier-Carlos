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
 * Clase AdaptadorMensajes.<br/>
 * La clase extiende de {@link ArrayAdapter ArrayAdapter}, es una clase creada para crear el modelo de
 * datos de la listview que nos muestra los mensajes, recogidos previamente de la base de datos.
 *
 * @author Carlos García Corpas y Javier Sánchez Gómez
 */
public class AdaptadorMensajes extends ArrayAdapter<Mensaje> {

    /**El listado de mensajes con los que se va a tratar.*/
    private ArrayList<Mensaje> listado;

    /**
     * Constructor por defecto de la clase. Se encarga de especificar el layout de cada elemento del listview a través del método super.
     * Además, recoge el ArrayList de Mensajes de donde vamos a sacar los datos.
     *
     * @param context El contexto de la aplicación.
     * @param objects La colección de mensajes que vamos a meter en el listview.
     */
    public AdaptadorMensajes(Context context, ArrayList<Mensaje> objects) {
        super(context, R.layout.listado_mensajes, objects);
        this.listado = objects;
    }

    /**
     * Este método se sobrescribe de la clase ArrayAdapter. Es el método encargado de inflar los layouts de cada uno de los mensajes que van a introducirse en el listview.
     * Ha sido optimizado utilizando las técnicas de usar la vista reciclada (el convertView) y un {@link MensajesHolder Holder}.
     *
     * @param position    La posición del mensaje.
     * @param convertView La vista reciclada. En caso de que no exista aún porque no se ha hecho scroll su valor es null.
     * @param parent      La vista padre.
     * @return La vista del mensaje ya formada.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        MensajesHolder holder;

        if (item == null) {
            LayoutInflater inflador = LayoutInflater.from(getContext());
            item = inflador.inflate(R.layout.listado_mensajes, null);

            holder = new MensajesHolder();
            holder.cuerpo = (TextView) item.findViewById(R.id.tvCuerpo);
            holder.hora = (TextView) item.findViewById(R.id.tvHora);
            holder.destinatario = (TextView) item.findViewById(R.id.tvDestinatarioH);
            holder.remitente = (TextView) item.findViewById(R.id.tvRemitenteH);
            holder.asunto = (TextView) item.findViewById(R.id.tvAsuntoH);
            item.setTag(holder);
        } else {
            holder = (MensajesHolder) item.getTag();
        }

        holder.destinatario.setText(listado.get(position).getDestinatario().getNombre());
        holder.remitente.setText(listado.get(position).getRemitente().getNombre());
        holder.asunto.setText(listado.get(position).getAsunto());
        holder.cuerpo.setText(listado.get(position).getCuerpoMensaje());
        holder.hora.setText(listado.get(position).getHora());

        return item;
    }
}
