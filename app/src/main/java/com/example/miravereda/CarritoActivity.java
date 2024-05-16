package com.example.miravereda;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

public class CarritoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    private RecyclerViewAdapterCarrito recyclerViewAdapterCarrito;

    private TextView precio;

    private ImageView carrito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_carrito);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        recyclerView=findViewById(R.id.recyclerViewcarrito);
        precio=findViewById(R.id.precio);
        carrito=findViewById(R.id.carrito);
        recyclerViewAdapterCarrito=new RecyclerViewAdapterCarrito(this);
        recyclerView.setAdapter(recyclerViewAdapterCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));





    }
}