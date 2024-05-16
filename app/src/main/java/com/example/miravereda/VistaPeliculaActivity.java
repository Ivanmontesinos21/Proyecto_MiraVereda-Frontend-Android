package com.example.miravereda;



import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;



import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class VistaPeliculaActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView titulo;
    private TextView autor;
    private TextView precio;
    private TextView nota;
    private TextView notaMedia;
    private RatingBar ratingBar;
    private Button btnComprar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_pelicula);

        imageView = findViewById(R.id.imageView);
        titulo = findViewById(R.id.titulo);
        autor = findViewById(R.id.autor);
        precio = findViewById(R.id.precio);
        nota = findViewById(R.id.nota);
        notaMedia = findViewById(R.id.notaMedia);
        ratingBar = findViewById(R.id.ratingBar);
        btnComprar = findViewById(R.id.btnComprar);

        Intent intent = getIntent();
        int imageResId = intent.getIntExtra("imagen", 0);
        String title = intent.getStringExtra("titulo");
        String author = intent.getStringExtra("autor");
        double price = intent.getDoubleExtra("precio", 0);
        float rating = intent.getFloatExtra("nota", 0);
        float averageRating = intent.getFloatExtra("notaMedia", 0);

        imageView.setImageResource(imageResId);
        titulo.setText(title);
        autor.setText(author);
        precio.setText("Precio: " + price);
        nota.setText("Nota: " + rating);
        notaMedia.setText("Nota Media: " + averageRating);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float newRating, boolean fromUser) {
                if (fromUser) {
                    float newAverageRating = (averageRating + newRating) / 2;
                    notaMedia.setText("Nota Media: " + newAverageRating);
                }
            }
        });

        btnComprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Aquí se añadiría la película al carrito
                // Por ejemplo, guardar en SharedPreferences, base de datos o una lista estática
            }
        });
    }
}
