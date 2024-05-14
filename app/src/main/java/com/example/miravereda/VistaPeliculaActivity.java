package com.example.miravereda;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VistaPeliculaActivity extends AppCompatActivity {
    ImageView ivpelicula;
    TextView tvtitulo;
    TextView tvdirector;
    TextView tvactor;
    EditText etprecio;
    RatingBar rbnota;
    int notamedia;
    int votos;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.vista_pelicula);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ivpelicula.findViewById(R.id.ivpelicula);
        tvtitulo.findViewById(R.id.tvtitulo);
        tvactor.findViewById(R.id.tvactor);
        etprecio.findViewById(R.id.etprecio);
        rbnota.findViewById(R.id.rbnota);



        public void votarPelicula(){
            rbnota.setOnClickListener(v -> {
                int rating= (int) rbnota.getRating();
                Toast.makeText(getApplicationContext(),rating,Toast.LENGTH_LONG).show();



        }


    }
    }
}






}
