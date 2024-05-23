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
    private TextView autor;
    private TextView precio;
    private TextView nota;
    private ContenidoAudiovisual contenidoAudiovisual;
    private TextView notaMedia;
    private RatingBar ratingBar;
    private Button btnComprar;
    private double totalCarrito;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pelicula);


        contenidoAudiovisual=(ContenidoAudiovisual) getIntent().getExtras().getSerializable("ca");
        imageView = findViewById(R.id.imagendetail);
        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        precio = findViewById(R.id.precio);
        nota = findViewById(R.id.nota);
        notaMedia = findViewById(R.id.notaMedia);
        ratingBar = findViewById(R.id.ratingBar);
        btnComprar = findViewById(R.id.btnComprar);
        ImageDownloader.downloadImage(contenidoAudiovisual.getImagenUrl(),imageView);
        titulo.setText(contenidoAudiovisual.getTitulo());
        autor.setText(contenidoAudiovisual.getNombreDirector());
        precio.setText(Double.toString((double)contenidoAudiovisual.getPrecio() / 100.0));
        notaMedia.setText(Double.toString(contenidoAudiovisual.getValoracionMedia() / 100.0));
        ratingBar.setRating((float) contenidoAudiovisual.getValoracionMedia());
        float averageRating =0;
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                if (fromUser) {
                    float newAverageRating = (averageRating + newRating) / 2;
                    notaMedia.setText("Nota Media: " + newAverageRating);
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
