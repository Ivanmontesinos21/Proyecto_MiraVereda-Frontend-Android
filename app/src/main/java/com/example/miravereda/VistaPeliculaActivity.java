package com.example.miravereda;



import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;

import com.example.miravereda.API.Connector;
import com.example.miravereda.base.BaseActivity;
import com.example.miravereda.base.CallInterface;
import com.example.miravereda.base.ImageDownloader;
import com.example.miravereda.model.AnyadirAlCarro;
import com.example.miravereda.model.ContenidoAudiovisual;
import com.example.miravereda.model.Credenciales;


public class VistaPeliculaActivity extends BaseActivity implements CallInterface {
    private ImageView imageView;
    private TextView titulo;
    private TextView descripcion;
    private TextView autor;
    private TextView precio;
    private ContenidoAudiovisual contenidoAudiovisual;
    private RatingBar ratingBar;
    private Button btnComprar;
    private double totalCarrito;

    /**
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pelicula);

        //inicializamos todo
        contenidoAudiovisual=(ContenidoAudiovisual) getIntent().getExtras().getSerializable("ca");
        imageView = findViewById(R.id.imagendetail);
        titulo = findViewById(R.id.titulo);
        descripcion = findViewById(R.id.descripcionDetail);
        autor = findViewById(R.id.autor);
        precio = findViewById(R.id.precio);
        ratingBar = findViewById(R.id.ratingBar);
        btnComprar = findViewById(R.id.btnComprar);
        ImageDownloader.downloadImage(contenidoAudiovisual.getImagenUrl(),imageView);
        titulo.setText(contenidoAudiovisual.getTitulo());
        descripcion.setText(contenidoAudiovisual.getDescripcion());
        autor.setText("Director: " + contenidoAudiovisual.getNombreDirector());
        precio.setText(((double)contenidoAudiovisual.getPrecio() / 100.0) + "€");
        ratingBar.setRating((float) contenidoAudiovisual.getValoracionMedia());
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                if (fromUser){



                }
            }
        });
        btnComprar.setOnClickListener(v -> {
            executeCall(this);
        });

    }

    @Override
    public void doInBackground() {
        SharedPreferences preferences=getSharedPreferences("usuario",MODE_PRIVATE);
        String mail=preferences.getString("mail",null);
        String contrasenya=preferences.getString("contrasenya",null);
        AnyadirAlCarro carro=new AnyadirAlCarro(mail,contrasenya,contenidoAudiovisual.getId());
        totalCarrito = (double)Connector.getConector().post(Integer.class,carro,"carrito/") / 100.0;
    }

    @Override
    public void doInUI() {
        Toast.makeText(this, titulo.getText() + " se ha añadido al carrito. Total del carrito: " + totalCarrito + "€", Toast.LENGTH_LONG).show();
        finish();
    }
}
