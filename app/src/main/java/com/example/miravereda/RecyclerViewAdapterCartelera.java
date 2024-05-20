package com.example.miravereda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miravereda.model.ContenidoAudiovisual;

public class RecyclerViewAdapterCartelera extends RecyclerView.Adapter<ViewHolder2> {

    public Context context;



    public LayoutInflater layoutInflater;

    public RecyclerViewAdapterCartelera(Context context){
        super();
        this.context=context;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @NonNull
    @Override
    public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.recycler_items_catalogo_peliculas,parent,false);
        return new ViewHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
        holder.imagen.setImageResource();
        holder.titulo.setText();
        holder.estrellas.

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ViewHolder2 extends RecyclerView.ViewHolder{

    ImageView imagen;

    TextView titulo;

    RatingBar estrellas;

    public ViewHolder2(@NonNull View itemView) {
        super(itemView);
        imagen=itemView.findViewById(R.id.fotocartelera);
        titulo=itemView.findViewById(R.id.titulocartelera);
        estrellas=itemView.findViewById(R.id.notacartelera);
    }


}