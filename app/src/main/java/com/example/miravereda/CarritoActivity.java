package com.example.miravereda;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.TextView;

import com.example.miravereda.API.Connector;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.model.ContenidoAudiovisual;
import com.example.miravereda.model.Credenciales;

import java.util.List;

public class CarritoActivity extends BaseActivity implements CallInterface {

    private RecyclerView recyclerView;

    private RecyclerViewAdapterCarrito recyclerViewAdapterCarrito;

    private TextView precio;

    private ImageView carrito;

    private List<ContenidoAudiovisual> peliculas;


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

        precio=findViewById(R.id.precio);
        carrito=findViewById(R.id.imagencarrito);


    }

    @Override
    public void doInBackground() {
        SharedPreferences prefs=getSharedPreferences("usuario", MODE_PRIVATE);
        String mail=prefs.getString("mail",null);
        String contrasenya=prefs.getString("contrasenya",null);
        Credenciales credenciales=new Credenciales(
                mail,contrasenya
        );
        Connector.getConector().post(Credenciales.class,credenciales,"carrito/ver/");
        peliculas= Connector.getConector().getAsList(ContenidoAudiovisual.class,"carrito/ver/");
    }

    @Override
    public void doInUI() {
        hideProgress();
        recyclerView=findViewById(R.id.recyclerViewcarrito);
        recyclerViewAdapterCarrito=new RecyclerViewAdapterCarrito(this,peliculas);
        recyclerView.setAdapter(recyclerViewAdapterCarrito);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }
}