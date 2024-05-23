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

    public RecyclerViewAdapterCartelera(SecondScreen activity, List<ContenidoAudiovisual> contenidosAudiovisuales){
        super();
        this.activity = activity;
        this.contenidosAudiovisuales = contenidosAudiovisuales;
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.recycler_items_catalogo_peliculas,parent,false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        if(position < contenidosAudiovisuales.size()) {
            ContenidoAudiovisual ca = contenidosAudiovisuales.get(position);
            String imagenUrl = ca.getImagenUrl();
            if (imagenUrl != null)
                ImageDownloader.downloadImage(imagenUrl, holder.imagen);
            holder.titulo.setText(ca.getTitulo());
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

    @Override
    public int getItemCount() {
        return contenidosAudiovisuales.size() + 1;
    }
}

class ViewHolder2 extends RecyclerView.ViewHolder{

    View main;
    View progress;
    ImageView imagen;
    TextView titulo;
    TextView descripcion;
    RatingBar estrellas;

    public ViewHolder2(@NonNull View itemView) {
        super(itemView);
        main = itemView.findViewById(R.id.item_catalogo_main);
        progress = itemView.findViewById(R.id.item_catalogo_progress);
        imagen=itemView.findViewById(R.id.fotocartelera);
        titulo=itemView.findViewById(R.id.titulocartelera);
        descripcion=itemView.findViewById(R.id.descripcioncartelera);
        estrellas=itemView.findViewById(R.id.notacartelera);
    }


}