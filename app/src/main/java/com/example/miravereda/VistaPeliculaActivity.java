package com.example.miravereda;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

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
    double notamedia;

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
        ivpelicula=findViewById(R.id.ivpelicula);
        tvtitulo=findViewById(R.id.tvtitulo);
        tvactor=findViewById(R.id.tvactor);
        etprecio=findViewById(R.id.etprecio);
        rbnota=findViewById(R.id.rbnota);

    }





}
