package com.example.miravereda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miravereda.base.ImageDownloader;
import com.example.miravereda.model.ContenidoAudiovisual;

import java.util.List;

public class RecyclerViewAdapterCarrito extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;
    List<ContenidoAudiovisual> contenidoAudiovisuales;


    public RecyclerViewAdapterCarrito(Context context, List<ContenidoAudiovisual> contenidoAudiovisuals){
        super();
        this.context=context;
        this.contenidoAudiovisuales=contenidoAudiovisuals;
        layoutInflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.recycler_item_carrito,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ContenidoAudiovisual c=contenidoAudiovisuales.get(position);
        ImageDownloader.downloadImage(c.getImagenUrl(), holder.imagen);
        holder.tvtitulopelicula.setText(c.getTitulo());
        //holder.tvpreciopelicula.setText();

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}


class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imagen;
    TextView tvtitulopelicula;

    TextView tvpreciopelicula;

    ConstraintLayout constraintLayout;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imagen=itemView.findViewById(R.id.foto_recycler);
        tvtitulopelicula=itemView.findViewById(R.id.titulo_recycler);
        tvpreciopelicula=itemView.findViewById(R.id.tvprecio_recycler);
        constraintLayout=itemView.findViewById(R.id.constraintRecycler);
    }
}
