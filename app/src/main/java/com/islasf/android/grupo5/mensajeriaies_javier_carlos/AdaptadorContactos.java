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
 * Clase AdaptadorContactos.<br/>
 * La clase extiende de {@link ArrayAdapter ArrayAdapter}, es una clase creada para crear el modelo de
 * datos de la listview que nos muestra los contactos, recogidos previamente de la base de datos.
 *
 * @author Carlos García Corpas y Javier Sánchez Gómez
 */
public class AdaptadorContactos extends ArrayAdapter<Contacto> {

    /**El ArrayList de contactos a tratar.*/
    private ArrayList<Contacto> contactos;

    /**
     * Constructor por defecto de la clase. Se encarga de especificar el layout de cada elemento del listview a través del método super.
     * Además, recoge el ArrayList de Contactos de donde vamos a sacar los datos.
     *
     * @param context   El contexto de la aplicación.
     * @param contactos La colección de contactos que vamos a meter en el listview.
     */
    public AdaptadorContactos(Context context, ArrayList<Contacto> contactos) {
        super(context, R.layout.listado_contactos, contactos);
        this.contactos = contactos;
    }

    /**
     * Este método se sobrescribe de la clase ArrayAdapter. Es el método encargado de inflar los layouts de cada uno de los contactos que van a introducirse en el listview.
     * Ha sido optimizado utilizando las técnicas de usar la vista reciclada (el convertView) y un {@link ContactosHolder Holder}.
     *
     * @param position    La posición del contacto.
     * @param convertView La vista reciclada. En caso de que no exista aún porque no se ha hecho scroll su valor es null.
     * @param parent      La vista padre. ViewGroup es literalmente de lo que extienden todos los layouts y vistas contenedoras de vistas.
     * @return La vista del contacto ya formada.
     */
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ContactosHolder holder;

        if (item == null) {
            LayoutInflater inflador = LayoutInflater.from(getContext());
            item = inflador.inflate(R.layout.listado_contactos, null);

            holder = new ContactosHolder();
            holder.nombre = (TextView) item.findViewById(R.id.tvNombre);
            holder.numero = (TextView) item.findViewById(R.id.tvTelefono);
            holder.email = (TextView) item.findViewById(R.id.tvEmail);
            item.setTag(holder);
        } else {
            holder = (ContactosHolder) item.getTag();
        }

        holder.nombre.setText(contactos.get(position).getNombre());
        holder.numero.setText(contactos.get(position).getTelefono());
        holder.email.setText(contactos.get(position).getEmail());

        return item;
    }
}
