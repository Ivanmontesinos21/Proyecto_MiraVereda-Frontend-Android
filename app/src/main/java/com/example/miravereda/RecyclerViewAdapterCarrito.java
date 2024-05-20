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

public class RecyclerViewAdapterCarrito extends RecyclerView.Adapter<MyViewHolder> {

    private Context context;
    private LayoutInflater layoutInflater;


    public RecyclerViewAdapterCarrito(Context context){
        super();
        this.context=context;
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
        /*
        holder.imagen.setImageResource();
        holder.tvtitulopelicula.setText();
        holder.tvpreciopelicula.setText();
        */
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
