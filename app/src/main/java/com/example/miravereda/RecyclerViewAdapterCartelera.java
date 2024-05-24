package com.example.miravereda;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.ImageDownloader;
import com.example.miravereda.base.Parameters;
import com.example.miravereda.model.ContenidoAudiovisual;

import java.util.List;

public class RecyclerViewAdapterCartelera extends RecyclerView.Adapter<ViewHolder2> {
    public SecondScreen activity;


    public List<ContenidoAudiovisual> contenidosAudiovisuales;


    public LayoutInflater layoutInflater;

    /**
     *
     * @param activity La actividad sobre la que va a trabajar
     * @param contenidosAudiovisuales La lista sobre la que va a trabajar
     */

    public RecyclerViewAdapterCartelera(SecondScreen activity, List<ContenidoAudiovisual> contenidosAudiovisuales){
        super();
        this.activity = activity;
        this.contenidosAudiovisuales = contenidosAudiovisuales;
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    /**
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     *               an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return el viewholder
     */
    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.recycler_items_catalogo_peliculas,parent,false);
        return new ViewHolder2(view);
    }

    /**
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        if(position < contenidosAudiovisuales.size()) {
            holder.main.setVisibility(View.VISIBLE);
            holder.progress.setVisibility(View.INVISIBLE);
            ContenidoAudiovisual ca = contenidosAudiovisuales.get(position);
            String imagenUrl = ca.getImagenUrl();
            if (imagenUrl != null)
                ImageDownloader.downloadImage(imagenUrl, holder.imagen);
            holder.titulo.setText(ca.getTitulo());
            double precio = (double)ca.getPrecioConTarifa() / 100.0;
            holder.precio.setText(precio + "€");
            holder.descripcion.setText(ca.getDescripcion());
            holder.estrellas.setRating((float) ca.getValoracionMedia());
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(holder.itemView.getContext(), VistaPeliculaActivity.class);
                intent.putExtra("ca", ca);
                System.out.println(" " + ca);
                holder.itemView.getContext().startActivity(intent);

            });
        }
        else {
            holder.main.setVisibility(View.INVISIBLE);
            holder.progress.setVisibility(View.VISIBLE);
            activity.loadMore();
        }
    }

    /**
     *
     * @return el tamaño de la lista +1
     */

    @Override
    public int getItemCount() {
        return contenidosAudiovisuales.size() + 1;
    }
}

/**
 * La clase ViewHolder2
 */

class ViewHolder2 extends RecyclerView.ViewHolder{

    View main;
    View progress;
    ImageView imagen;
    TextView titulo;
    TextView precio;
    TextView descripcion;
    RatingBar estrellas;

    public ViewHolder2(@NonNull View itemView) {
        super(itemView);
        main = itemView.findViewById(R.id.item_catalogo_main);
        progress = itemView.findViewById(R.id.item_catalogo_progress);
        imagen=itemView.findViewById(R.id.fotocartelera);
        titulo=itemView.findViewById(R.id.titulocartelera);
        precio=itemView.findViewById(R.id.preciocartelera);
        descripcion=itemView.findViewById(R.id.descripcioncartelera);
        estrellas=itemView.findViewById(R.id.notacartelera);
    }


}