package com.example.miravereda;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miravereda.base.ImageDownloader;
import com.example.miravereda.model.ContenidoAudiovisual;

import java.util.List;

public class RecyclerViewAdapterCarrito extends RecyclerView.Adapter<MyViewHolder> {

    private CarritoActivity activity;
    private LayoutInflater layoutInflater;
    List<ContenidoAudiovisual> contenidoAudiovisuales;


    public RecyclerViewAdapterCarrito(CarritoActivity activity, List<ContenidoAudiovisual> contenidoAudiovisuals){
        super();
        this.activity = activity;
        this.contenidoAudiovisuales=contenidoAudiovisuals;
        layoutInflater=(LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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
        holder.titulopelicula.setText(c.getTitulo());
        double precio = (double)c.getPrecioConTarifa() / 100.0;
        holder.preciopelicula.setText(precio + "€");
        holder.descripcionpelicula.setText(c.getDescripcion());
        holder.borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.quitarDelCarrito(c.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return contenidoAudiovisuales.size();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder{
    ImageView imagen;
    ImageButton borrar;
    TextView titulopelicula;

    TextView preciopelicula;

    TextView descripcionpelicula;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        imagen=itemView.findViewById(R.id.fotocarrito);
        borrar=itemView.findViewById(R.id.borrarcarrito);
        titulopelicula=itemView.findViewById(R.id.titulocarrito);
        preciopelicula=itemView.findViewById(R.id.preciocarrito);
        descripcionpelicula=itemView.findViewById(R.id.descripcioncarrito);
    }
}
